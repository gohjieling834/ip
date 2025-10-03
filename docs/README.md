# Berry User Guide

<img width="374" height="479" alt="Image" src="https://github.com/user-attachments/assets/2710709e-3098-4fc1-9bef-273a37fcda46" />

Berry is a personal assistant chatbot that helps you manage and keep track of your tasks. It is optimized for use
through a Command Line Interface (CLI), making it especially efficient for fast typists. Compared to traditional
GUI-based apps, Berry provides a quicker way to stay organized.

## Features

> Notes about command format:
> - Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a
    parameter which can be used as todo Read.
>
>
> - Commands should all be in lower case. e.g. the `find` command should be **find** and **not** Find or FIND.
>
>
> - Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. e.g.
    if the command specifies `list 231`, it will be interpreted as `list`.

### Adding todos

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Examples:

- `todo Read book`
- `todo CS2113 Quiz`

Expected Outcome:

```
--------------------------------------------
Got it. I've added this task:
  [T][ ] Read book
Now you have 1 tasks in the list.
--------------------------------------------
```

### Adding deadlines

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

Examples:

- `deadline Do CS2113 Quiz /by 7 Oct 9pm`
- `deadline Submit report /by 11.59pm`
- `deadline Finish book /by 8 Oct`

Expected Outcome:

```
--------------------------------------------
Got it. I've added this task:
  [D][ ] Do CS2113 Quiz (by: 7 Oct 9pm)
Now you have 2 tasks in the list.
--------------------------------------------
```

### Adding events

Adds an event task to the task list.

Format: `event DESCRIPTION /from DATETIME /to DATETIME`

Examples:

- `event Lecture /from 4pm /to 6pm`
- `event Cat Expo /from 29 Aug /to 31 Aug`

Expected Outcome:

```
--------------------------------------------
Got it. I've added this task:
  [E][ ] Lecture (from: 4pm to: 6pm)
Now you have 3 tasks in the list.
--------------------------------------------
```

### Listing all tasks

Shows a list of all tasks in the task list.

Format: `list`

Expected Outcome:

```
--------------------------------------------
1.[T][ ] Read book
2.[D][ ] Do CS2113 Quiz (by: 7 Oct 9pm)
3.[E][ ] Lecture (from: 4pm to: 6pm)
--------------------------------------------
```

### Deleting a task

Deletes a task from the task list.

Format: `delete NUMBER`

Examples:

- `delete 1`

Expected Outcome:

```
--------------------------------------------
Okay, I've removed this task:
  [T][ ] Read book
Now you have 2 tasks in the list.
--------------------------------------------
```

### Marking a task

Marks a task as done in the task list.
> **Note:** Marking a task that is marked will show a message saying that the task is already marked.

Format: `mark NUMBER`

Example:

- `mark 1`

Expected Outcome:

```
--------------------------------------------
Nice! I've marked this task as done:
  [T][X] Read book
--------------------------------------------
```

### Unmarking a task

Marks a task as **not** done in the task list.
> **Note:** Unmarking a task that is unmarked will show a message saying that the task is already unmarked. 

Format: `unmark NUMBER`

Example:

- `unmark 1`

Expected Outcome:

```
--------------------------------------------
Okay, I've marked this task as not done yet:
  [T][ ] Read book
--------------------------------------------
```

### Finding a task

Finds tasks that contains the keyword in the description.

Format: `find KEYWORD`

Examples:

- `find book`

Expected Outcome:

```
--------------------------------------------
Here are the matching tasks in your list:
1.[T][ ] Read book
--------------------------------------------
```

### Exiting the program

Exits the program.

Format: `bye`

### Saving the data

Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to
save the data manually.