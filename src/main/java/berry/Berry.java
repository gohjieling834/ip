package berry;

import berry.task.Deadline;
import berry.task.Event;
import berry.task.Task;
import berry.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Berry {
    public static final String DIVIDER = "‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧";
    public static final String CHATBOT_NAME = "Berry";
    public static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();

        printHelloMessage();
        while (true) {
            userInput = getUserInput(IN);
            executeCommand(userInput, tasks);
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

    public static void printErrorMessage(String errorMessage) {
        System.out.println("\n" + DIVIDER + "\n" + errorMessage + "\n" + DIVIDER + "\n");
    }

    public static void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + DIVIDER + "\n");
    }

    public static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    public static String extractCommand(String userInput) {
        String userCommand;
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
        } else {
            throw new BerryException("Sorry, I'm not sure what you want me to do ._.");
        }
        return userCommand;
    }

    public static String[] extractDetails(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        String task = userInput.substring(dividerPosition + 1);
        return task.split("/");
    }

    public static void executeCommand(String userInput, ArrayList<Task> tasks) {
        try {
            String userCommand = extractCommand(userInput);
            switch (userCommand) {
            case "list":
                printList(tasks);
                break;
            case "todo":
                addTodo(userInput, tasks);
                break;
            case "deadline":
                addDeadline(userInput, tasks);
                break;
            case "event":
                addEvent(userInput, tasks);
                break;
            case "mark":
                toggleTaskStatus(userInput, tasks);
                break;
            case "bye":
                printByeMessage();
                System.exit(0);
                // Fallthrough
            }
        } catch (BerryException e) {
            System.out.println("\n" + DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void addTodo(String userInput, ArrayList<Task> tasks) {
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description));
        printAddTaskMessage(tasks);
    }

    public static void addDeadline(String userInput, ArrayList<Task> tasks) {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();
        tasks.add(new Deadline(description, by));
        printAddTaskMessage(tasks);
    }

    public static void addEvent(String userInput, ArrayList<Task> tasks) {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Please enter all the event detail (description, from, to). Thank you :)");
        }
        int startIndexOfFrom = taskDetails[1].indexOf("from") + 4;  // + 4 because the substring start index should begin after from
        int startIndexOfTo = taskDetails[2].indexOf("to") + 2;  // + 2 because the substring start index should begin after to
        String description = taskDetails[0].trim();
        String from = taskDetails[1].substring(startIndexOfFrom).trim();
        String to = taskDetails[2].substring(startIndexOfTo).trim();
        tasks.add(new Event(description, from, to));
        printAddTaskMessage(tasks);
    }

    public static void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        System.out.println("\n" + DIVIDER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(DIVIDER + "\n");
    }

    public static void toggleTaskStatus(String userInput, ArrayList<Task> tasks) {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            if (userInput.contains("un")) {
                tasks.get(taskNumber).markAsUndone();
                System.out.println("\n" + DIVIDER + "\n" + "Okay, I've marked this task as not done yet:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            } else {
                tasks.get(taskNumber).markAsDone();
                System.out.println("\n" + DIVIDER + "\n" + "Nice! I've marked this task as done:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            }
        } catch (NumberFormatException e) {
            printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (NullPointerException e) {
            printErrorMessage("This task number does not exist! :|");
        }
    }
}