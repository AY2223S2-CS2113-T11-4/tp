package seedu.todolist.logic;

import org.junit.jupiter.api.Test;
import seedu.todolist.constants.Formats;
import seedu.todolist.exception.InvalidDateException;
import seedu.todolist.exception.InvalidDurationException;
import seedu.todolist.exception.InvalidEmailFormatException;
import seedu.todolist.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilTest {
    @Test
    void parseIdTest() {
        // Invalid ids should throw an exception
        final String[] invalid_ids = {"2.5", "", "abc"};
        for (String invalidId : invalid_ids) {
            assertThrows(InvalidIdException.class, () -> ParserUtil.parseId(invalidId));
        }

        // Valid ids get parsed successfully
        final int[] valid_ids = {3, 17, 84725};
        try {
            for (int validId : valid_ids) {
                assertEquals(ParserUtil.parseId(Integer.toString(validId)), validId);
            }
        } catch (InvalidIdException e) {
            fail("Valid id was not successfully parsed.");
        }
    }

    @Test
    void parseDeadlineTest() {
        // Invalid dates should throw an exception
        final String[] invalid_deadlines = {"30/02/2023 16:40", "lalala", "10-50-2032 22:06", ""};
        for (String invalidDeadline : invalid_deadlines) {
            assertThrows(InvalidDateException.class, () -> ParserUtil.parseDeadline(invalidDeadline));
        }

        // Valid dates get parsed successfully
        final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime[] valid_deadlines = {now, now.plusDays(1), now.minusWeeks(1), now.plusYears(10)};
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_IN_1.getFormat());
        try {
            for (LocalDateTime validDeadline : valid_deadlines) {
                assertEquals(ParserUtil.parseDeadline(validDeadline.format(inputFormatter)), validDeadline);
            }
        } catch (InvalidDateException e) {
            fail("Valid deadline was not successfully parsed.");
        }
    }

    @Test
    void parseEmailTest() {
        // Invalid emails should throw an exception
        final String[] invalid_emails = {"a@b@c@d.com", "example.com", "hello", "", "where@how"};
        for (String invalidEmail : invalid_emails) {
            assertThrows(InvalidEmailFormatException.class, () -> ParserUtil.parseEmail(invalidEmail));
        }

        // Valid emails get parsed successfully
        final String[] valid_emails = {"xyz@xyz.com", "Idk@lalala.com", "legit@gmail.com"};
        try {
            for (String validEmail : valid_emails) {
                assertEquals(ParserUtil.parseEmail(validEmail), validEmail);
            }
        } catch (InvalidEmailFormatException e) {
            fail("Valid email address was not successfully parsed.");
        }
    }

    @Test
    void parseRepeatDurationTest() {
        // Invalid repeat durations should throw an exception
        final String[] invalid_durations = {"$^8g", "1.36", "vb 2s"};
        for (String invalidDuration : invalid_durations) {
            assertThrows(InvalidDurationException.class,
                    () -> ParserUtil.parseRepeatDuration(invalidDuration, LocalDateTime.now()));
        }

        // If repeat duration is not provided, it should default to 0
        try {
            assertEquals(ParserUtil.parseRepeatDuration(null, LocalDateTime.now()), 0);
        } catch (InvalidDateException | InvalidDurationException e) {
            fail("Missing repeat duration was not automatically parsed as 0.");
        }

        // Valid repeat durations get parsed successfully
        final int[] valid_durations = {3, 17, 84725};
        try {
            for (int validDuration : valid_durations) {
                assertEquals(ParserUtil.parseRepeatDuration(Integer.toString(validDuration),
                        LocalDateTime.now()), validDuration);
            }
        } catch (InvalidDateException | InvalidDurationException e) {
            fail("Valid repeat duration was not successfully parsed.");
        }

        // Missing deadline should throw an exception
        for (int validDuration : valid_durations) {
            assertThrows(InvalidDateException.class,
                    () -> ParserUtil.parseRepeatDuration(Integer.toString(validDuration), null));
        }
    }
}
