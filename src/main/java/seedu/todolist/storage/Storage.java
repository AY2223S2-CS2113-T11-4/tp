//@@author jeromeongithub
package seedu.todolist.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;
import seedu.todolist.constants.Formats;
import seedu.todolist.exception.FailedLoadException;
import seedu.todolist.exception.FailedSaveException;
import seedu.todolist.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class for saving the task list as a text file and loading it.
 */
public class Storage {
    public static final String DEFAULT_SAVE_PATH = "./data.txt";
    private boolean isNewSave;
    private File file;
    private Gson gson;

    /*referred closely to CS2113T-T09-1's LocalDate Adapter in
    https://github.com/AY2223S2-CS2113T-T09-1/tp/blob/master/src/main/java/seedu/rainyDay/modules/Storage.java */
    public static class LocalDateTimeAdapter
            implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.
                ofPattern(Formats.TIME_IN_2);

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(localDateTime.format(dateTimeFormat));
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), dateTimeFormat);
        }
    }

    public Storage(String filepath) {
        assert filepath != null : "NULL filepath was given";
        file = new File(filepath);
        isNewSave = !file.exists();
        gson = new GsonBuilder().setPrettyPrinting().
                registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }

    // code provided by module website
    private void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the current task list to the local save file.
     *
     * @param taskList The task list being saved.
     * @throws FailedSaveException If the save file cannot be saved.
     */
    public void saveData(TaskList taskList, String filepath) throws FailedSaveException {
        try {
            String json = gson.toJson(taskList);
            writeToFile(filepath, json);
        } catch (IOException e) {
            throw new FailedSaveException();
        }
    }

    /**
     * Loads the task list from the local save file, if it exists.
     *
     * @return The task list read from the save file, if it exists and it is not empty. Otherwise, a newly created
     *         task list is returned.
     * @throws FileNotFoundException If no save file is found.
     * @throws FailedLoadException If the save file cannot be loaded.
     */

    public TaskList loadData(String filepath)
            throws FileNotFoundException, FailedLoadException {
        try {
            JsonReader reader = new JsonReader(new FileReader(filepath));
            TaskList taskList = new TaskList();
            // if the file is not empty, set the task list as the saved task list
            TaskList savedTaskList = gson.fromJson(reader, TaskList.class);
            if (savedTaskList != null) {
                taskList = savedTaskList;
            }
            return taskList;
        } catch (JsonParseException | DateTimeParseException e) {
            throw new FailedLoadException();
        }
    }
}
