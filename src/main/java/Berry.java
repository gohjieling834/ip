import java.util.Scanner;

public class Berry {
    public static final String DIVIDER = "‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧";
    public static final String CHATBOT_NAME = "Berry";
    public static final int TASK_SIZE = 100;

    public static void main(String[] args) {
        String userInput;
        Task[] tasks = new Task[TASK_SIZE];   //array of Task objects
        int taskCount = 0;

        printHelloMessage();
        while (true) {
            userInput = getUserInput();  //get command from user
            taskCount = executeCommand(userInput, tasks, taskCount);
        }
    }

    public static void printHelloMessage() {
        System.out.println(DIVIDER + "\n" + "Hello! I'm " + CHATBOT_NAME
                + "\nWhat can I do for you?" + "\n" + DIVIDER + "\n");
    }

    public static void printByeMessage() {
        System.out.println("\n" + DIVIDER + "\nBye. Hope to see you again soon!"
                + "\n" + DIVIDER);
    }

    public static void printInvalidMessage() {
        System.out.println("\n" + DIVIDER + "\nInvalid Command. Please try again!\n" + DIVIDER + "\n");
    }

    public static void printAddTaskMessage(Task task, int taskCount) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.\n" + DIVIDER + "\n");
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static String extractCommand(String userInput) {
        String userCommand = "invalid command";
        if (userInput.contains("list")) {
            userCommand = "list";
        } else if (userInput.contains("todo")) {
            userCommand = "todo";
        } else if (userInput.contains("deadline")) {
            userCommand = "deadline";
        } else if (userInput.contains("event")) {
            userCommand = "event";
        } else if (userInput.contains("mark")) {
            userCommand = "mark";
        } else if (userInput.contains("bye")) {
            userCommand = "bye";
        }
        return userCommand;
    }

    public static int executeCommand(String userInput, Task[] tasks, int taskCount) {
        String userCommand = extractCommand(userInput);
        switch (userCommand) {
        case "list":
            printList(tasks, taskCount);
            break;
        case "todo":
            taskCount++;
            addTodo(userInput, tasks, taskCount);
            break;
        case "deadline":
            taskCount++;
            addDeadline(userInput, tasks, taskCount);
            break;
        case "event":
            taskCount++;
            addEvent(userInput, tasks, taskCount);
            break;
        case "mark":
            toggleTaskStatus(userInput, tasks);
            break;
        case "bye":
            printByeMessage();
            System.exit(0);
            // Fallthrough
        case "invalid command":
            printInvalidMessage();
            break;
        }
        return taskCount;
    }

    public static void addTodo(String command, Task[] tasks, int taskCount) {
        String description = command.substring(5).trim();
        tasks[taskCount - 1] = new Todo(description);
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    public static void addDeadline(String command, Task[] tasks, int taskCount) {
        int indexOfBy = command.indexOf("by");
        String description = command.substring(9, indexOfBy - 1).trim();
        String by = command.substring(indexOfBy + 3);
        tasks[taskCount - 1] = new Deadline(description, by);
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    public static void addEvent(String command, Task[] tasks, int taskCount) {
        int indexOfFrom = command.indexOf("from");
        int indexOfTo = command.indexOf("to");
        String description = command.substring(6, indexOfFrom - 1).trim();
        String from = command.substring(indexOfFrom + 5, indexOfTo - 1).trim();
        String to = command.substring(indexOfTo + 3);
        tasks[taskCount - 1] = new Event(description, from, to);
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    public static void printList(Task[] tasks, int taskCount) {
        System.out.println("\n" + DIVIDER);
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
        System.out.println(DIVIDER + "\n");
    }

    public static void toggleTaskStatus(String command, Task[] tasks) {
        int dividerPosition = command.indexOf(" ");

        //task number to identify which task to mark/unmark
        int taskNumber = Integer.parseInt(command.substring(dividerPosition).trim()) - 1;
        System.out.println("\n" + DIVIDER);
        if (command.contains("un")) {
            tasks[taskNumber].markAsUndone();
            System.out.println("Okay, I've marked this task as not done yet:\n[ ] "
                    + tasks[taskNumber].getDescription() + "\n" + DIVIDER + "\n");
        } else {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] "
                    + tasks[taskNumber].getDescription() + "\n" + DIVIDER + "\n");
        }
    }
}
