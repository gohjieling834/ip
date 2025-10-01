package berry.ui;

import berry.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧";
    public static final String CHATBOT_NAME = "Berry";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printHelloMessage() {
        System.out.println(DIVIDER + "\n" + "Hello! I'm " + CHATBOT_NAME
                + "\nWhat can I do for you?" + "\n" + DIVIDER + "\n");
    }

    public void printByeMessage() {
        System.out.println("\n" + DIVIDER + "\nBye. Hope to see you again soon!"
                + "\n" + DIVIDER);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("\n" + DIVIDER + "\n" + errorMessage + "\n" + DIVIDER + "\n");
    }

    public void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + DIVIDER + "\n");
    }

    public void printDeleteTaskMessage(Task task, int listSize) {
        System.out.println("\n" + DIVIDER + "\n" + "Okay, I've removed this task:\n  "
                + task + "\n" + "Now you have " + listSize + " tasks in the list.\n"
                + DIVIDER + "\n");
    }

    public void printMarkTaskMessage(Task task, String message) {
        System.out.println("\n" + DIVIDER + "\n" + message
                + task + "\n" + DIVIDER + "\n");
    }

    public void printList(ArrayList<Task> tasks, String message) {
        System.out.println("\n" + DIVIDER);
        printListMessage(message);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(DIVIDER + "\n");
    }

    public void printListMessage(String message) {
        System.out.print(message);
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
