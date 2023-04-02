# User Guide

* Table of Contents
{:toc}

## Introduction

With the transition to Canvas, the most important feature of LumiNUS’s deadline reminders is gone! **NUS To-Do List** aims to
bring an application to keep you aware of your deadlines and not miss them.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `NUS To-Do List` from [here](https://github.com/AY2223S2-CS2113-T11-4/tp/releases/tag/v2.0).
3. Double-click the downloaded jar file or run `java -jar todolist.jar` in a terminal to start the program.

## Features 

#### Notes about the command format

- **Flags**
  - Words with a dash in front such as `-due` or `-email`, are flags. Hence, parameters like the description or tags cannot start with a dash.
  - Each command has its own flags that need to be provided when running the command.
  - You **cannot** repeat the same flag (`-edit abc -edit def`) for a command.
  - Flags in square brackets `[]` are optional. (Do not include the brackets in the command)
- **Parameters**
  - Words in `UPPER_CASE` are parameters to be supplied by the user.
  - Parameters cannot start with a dash `-`. For example, `add -random_name` is an invalid command.
  - Some parameters have additional restrictions on them; refer to the explanation for each command for more information.
  - Flags and their parameters can be provided in any order after the command name and its parameter. For example, instead of `add DESCRIPTION -due DATE -email ADDRESS` you can also use `add DESCRIPTION -email ADDRESS -due DATE`.
  - Unnecessary parameters for commands/flags that do not need them are ignored. For example, `exit 123` is equivalent to `exit`.
- **Dates**
  - The date-time format is `dd/mm/yyyy hh:mm` or `dd-mm-yyyy hh:mm`. An example of a valid date-time is `14/03/2025 16:40`.
- **Others**
  - Trailing, leading or consecutive spaces will be removed from commands.

### Adding a task `add`

Adds a new task to your To-Do list.

Format: `add DESCRIPTION [-due DEADLINE] [-email EMAIL_ADDRESS] [-tags LIST_OF_TAGS] [-rep REPEAT_DURATION] [-prio PRIORITY_LEVEL]`

- The format for `DEADLINE` is `dd/mm/yyyy hh:mm` or `dd-mm-yyyy hh:mm`.
- `EMAIL_ADDRESS` must be of a valid email address format.
- `LIST_OF_TAGS` can consist of multiple tags, separated by a space. A task cannot have multiple of the same tag. Tags will be sorted in lexicographic order.
- `REPEAT_DURATION` must be an integer from 0 to 2147483647, and can only be used if a `DEADLINE` is also provided. The task will repeat every week starting from the `DEADLINE` for `REPEAT_DURATION` times.
- `PRIORITY_LEVEL` must be either 1, 2, or 3 (1: `Low`, 2: `Medium`, 3: `High`).

Example of usage and output: 

`add todo -due 23/09/3000 23:59` creates a task with the description `todo`, and the deadline `23 Sep 3000 23:59`.
```
Okay, I have added this task:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

`add do math homework -email abc@def.com -tags difficult later` creates a task with the description `do math homework`, email address `abc@def.com`, and the tags `difficult` and `later`.
```
Okay, I have added this task:
[ID:2]	[ ][do math homework]
```

### Mark a task (as complete) `mark`

Marks a task with the given id by the user as completed. 

Format: `mark ID`

- The `ID` has to be an id of a task that can be found in the To-Do list.

Example of usage and output:

`mark 1` marks the task with id 1 in the To-Do list as done.
```
Okay, I have marked this task as complete:
[ID:1]	[X][todo][Due: 23 Sep 3000 23:59]
```

### Unmark a task (as incomplete) `unmark`

Unmarks a task with the given id by the user as incomplete. 

Format: `unmark ID`

- The `ID` has to be an id of a task that can be found in the To-Do list.

Example of usage and output:

`unmark 1` unmarks the task with id 1 in the To-Do list, so it is considered not done yet.
```
Okay, I have marked this task as incomplete:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

### Delete a task `delete`

Removes the task with the given id by the user from the To-Do list.

Format: `delete ID`

- The `ID` has to be an id of a task that can be found in the To-Do list.

Example of usage:

`delete 1` deletes the task of id 1 from the To-Do list.
```
Okay, I have removed this task:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

###  Edit description of a task `desc`

Edits the description of a task with the given id in the ToDo List.

Format: `desc ID -edit DESCRIPTION`

- The `ID` has to be an id of a task that can be found in the To-Do list.

Example of usage:

`desc 1 -edit abc def` sets the description of the task to `abc def`.
```
Okay, I have edited the priority level of this task to [Medium]:
[ID:1]	[ ][abc def][Due: 15 Mar 2025 18:24]
```

###  Edit priority level of a task `prio`

Edits, or deletes the priority level of a task with the given id in the ToDo List.

Format: `prio ID -edit PRIORITY_LEVEL`

- The `ID` has to be an id of a task that can be found in the To-Do list.
- The `PRIORITY_LEVEL` must be either 1, 2, or 3 (1: `Low`, 2: `Medium`, 3: `High`).

Example of usage:

`prio 1 -edit 2` sets the priority level of the task of id 1 to 2 (Medium).
```
Okay, I have edited the priority level of this task to [Medium]:
[ID:1]	[ ][abc][Due: 15 Mar 2025 18:24]
```

### Edit/delete deadline `due`

Edits or deletes the deadline of a task with the given id in the To-Do List.

Format: `due ID -edit DEADLINE` or `due ID -del`
- The `ID` has to be an id of a task that can be found in the To-Do list.
- Use `-edit` to replace the deadline of the task with the newly specified deadline, or `-del` to delete it instead.
- If both flags are provided, `-edit` takes priority.
- At least one of the two flags must be provided.
- The format for `DEADLINE` is `dd/mm/yyyy hh:mm` or `dd-mm-yyyy hh:mm`.

Example of usage:

`due 1 -edit 30-03-2023 18:00` changes the deadline of the task of id 1 in the To-Do list to `30-03-2023 18:00`.
```
Okay, I have edited the deadline of this task to [30 Mar 2023 18:00]:
[ID:1]	[ ][todo][Due: 30 Mar 2023 18:00]
```

`due 1 -del` deletes the deadline of the task of id 1 in the To-Do list.
```
Okay, I have deleted the deadline of this task:
[ID:1]	[ ][todo]
```

Example of usage:
```
Okay, I have edited the priority level of this task to [Medium]:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

### Add/Edit/Delete an email `email`

Adds, edits, or deletes the email address of a task with the given id in the To-Do List. 

Format: `email ID -edit EMAIL_ADDRESS` or `email ID -del`

- The `ID` has to be an id of a task that can be found in the To-Do list.
- Use `-edit` to edit the email address of the task, or `-del` to delete it instead.
- If both flags are provided, `-edit` takes priority.
- At least one of the two flags must be provided.
- `EMAIL_ADDRESS` must be of a valid email address format.

Example of usage:

`email 1 -edit rui@gmail.com` adds the email address `rui@gmail.com` to the task of id 1 in the To-Do list.
```
Okay, I have edited the email address of this task to [rui@gmail.com]:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

### Add/Edit/Delete tags `tags`

Adds, edits, or deletes the tags of a task with the given id in the To-Do List.

Format: `tags ID -edit LIST_OF_TAGS` or `tags ID -del`

- The `ID` has to be an id of a task that can be found in the To-Do list.
- Use `-edit` to replace the tags of the task with the newly specified tags, or `-del` to delete them instead.
- If both flags are provided, `-edit` takes priority.
- At least one of the two flags must be provided.
- `LIST_OF_TAGS` can consist of multiple tags, separated by a space. A task cannot have multiple of the same tag. Tags will be sorted in lexicographic order.

Example of usage:

`tags 1 -edit difficult later` adds the tags `difficult` and `later` to the task of id 1 in the To-Do list.
```
Okay, I have edited the tags of this task to [difficult, later]:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

### View all tasks in To-Do list `list`

Display all tasks stored in the To-Do List.

Format: `list`
- Tasks found will be displayed in ascending order, sorted by deadline.
- Tasks without deadline will be placed at the bottom.

Example of usage:

`list`
```
Okay, here is your task list, with 3 tasks
[ID:2]	[ ][assignment][Due: 23 Mar 2023 18:00]
[ID:3]	[ ][coursemology homework][Due: 24 Mar 2023 13:00]
[ID:1]	[ ][homework]
```

### View detailed information of a task `info`

Display all the attributes (description, deadline, email, tags, repeat duration, priority level) of the task with
the given id in the To-Do List.

Format: `info ID`

- The `ID` has to be an id of a task that can be found in the To-Do list.

Example of usage:

`info 1` displays the details of the task with id 1 in the To-Do list.
```
Okay, here is the detailed information of this task:
ID: 1
Description: todo
Priority: Low
Due: 23 Sep 3000 23:59
Tags: difficult, later
Repeat duration: 6
```

### Show progress of tasks that are due this week `progress`

Displays the progress of and lists tasks that are due this week in To-Do list. Progress is shown in the form of a 
percentage (up to 2 decimal places) and a progress bar (where `=` denotes the proportion of tasks completed and `-` 
denotes the proportion of tasks that are left undone. Called automatically on startup.

Format: `progress`

Example of usage:

`progress` displays the progress of and lists tasks that are due this week
```
You have completed 33.33% of the 3 tasks due this week!
Progress: |================----------------------------------|
[ID:1]	[X][task1][Due: 30 Mar 2023 18:00]
[ID:2]	[ ][task2][Due: 30 Mar 2023 19:00]
[ID:3]	[ ][task3][Due: 31 Mar 2023 20:00]
```

### Exit program `exit`

Exits the program.

Format: `exit`

Example of usage:

`exit`
```
See you again, bye!
```

### [*Proposed*] List completion history `history`

Lists the task that have been completed before in the To-Do List.

Format: `history`
- Displays the tasks which were marked as completed in the previous week

Example of usage:

`history`
```
Here are the tasks which were completed in the past week:
[ID:1]	[X][todo][Due: 20 Mar 2023 18:00]
```

## FAQ

**How do I transfer my data to another computer?**
- Your task list is saved in the generated `data.txt` file.
- Copy the `save.txt` to the other computer and place it in the same location as the jar file for the program before running it. 

## Command Summary

| Action                               | Command                                                                                                                     |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Add a task                           | `add DESCRIPTION [-due DEADLINE] [-email EMAIL_ADDRESS] [-tags LIST_OF_TAGS] [-rep REPEAT_DURATION] [-prio PRIORITY_LEVEL]` |
| Mark task complete                   | `mark ID`                                                                                                                   |
| Mark task incomplete                 | `unmark ID`                                                                                                                 |
| Delete a task                        | `delete ID`                                                                                                                 |
| Change description of task           | `desc ID -edit DESCRIPTION`                                                                                                 |
| Change priority level of task        | `prio ID -edit PRIORITY_LEVEL`                                                                                              |
| Add/edit deadline to a task          | `due ID -edit DEADLINE`                                                                                                     |
| Delete deadline from a task          | `due ID -del`                                                                                                               |
| Add/edit email to a task             | `email ID -edit EMAIL_ADDRESS`                                                                                              |
| Delete email from a task             | `email ID -del`                                                                                                             |
| Add/edit tags to a task              | `tags ID -edit LIST_OF_TAGS`                                                                                                |
| Delete all tags from a task          | `tags ID -del`                                                                                                              |
| Add/edit repeat duration to a task   | `rep ID -edit REPEAT DURATION`                                                                                              |
| Delete repeat duration from a task   | `rep ID -del`                                                                                                               |
| List all tasks                       | `list`                                                                                                                      |
| Check all details of a task          | `info`                                                                                                                      |
| Check progress of current week tasks | `progress`                                                                                                                  |
| Exit program                         | `exit`                                                                                                                      |
