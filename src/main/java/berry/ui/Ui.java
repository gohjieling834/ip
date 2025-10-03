package berry.ui;

import berry.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interaction for the Berry chatbot.
 */
public class Ui {
    public static final String DIVIDER = "--------------------------------------------";
    public static final String CHATBOT_NAME = "Berry";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the hello message when the chatbot starts.
     */
    public void printHelloMessage() {
        System.out.println(DIVIDER + "\n" + "Hello! I'm " + CHATBOT_NAME
                + "\nWhat can I do for you?" + "\n" + DIVIDER + "\n");
    }

    /**
     * prints the bye message when the chatbot exits.
     */
    public void printByeMessage() {
        System.out.println("\n" + DIVIDER + "\nBye. Hope to see you again soon!"
                + "\n" + DIVIDER);
    }

    /**
     * Prints a formatted error message.
     *
     * @param errorMessage The error message to display.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println("\n" + DIVIDER + "\n" + errorMessage + "\n" + DIVIDER + "\n");
    }

    /**
     * Prints a message after a task has been added.
     *
     * @param tasks The current list of tasks.
     */
    public void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + DIVIDER + "\n");
    }

    /**
     * Prints a message after a task has been deleted.
     *
     * @param task     The current list of tasks.
     * @param listSize The new number of tasks remaining in the list.
     */
    public void printDeleteTaskMessage(Task task, int listSize) {
        System.out.println("\n" + DIVIDER + "\n" + "Okay, I've removed this task:\n  "
                + task + "\n" + "Now you have " + listSize + " tasks in the list.\n"
                + DIVIDER + "\n");
    }

    /**
     * Prints a message after updating a task's completion status.
     *
     * @param task    The task that was marked or unmarked.
     * @param message The status message to display.
     */
    public void printMarkTaskMessage(Task task, String message) {
        System.out.println("\n" + DIVIDER + "\n" + message
                + task + "\n" + DIVIDER + "\n");
    }

    /**
     * Prints the list of tasks with a custom header message.
     *
     * @param tasks   The list of tasks to display.
     * @param message The message to display before printing the list.
     */
    public void printList(ArrayList<Task> tasks, String message) {
        System.out.println("\n" + DIVIDER);
        System.out.print(message);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The user input as a string.
     */
    public String getUserInput() {
        return in.nextLine();
    }
}
