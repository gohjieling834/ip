package berry.command;

import berry.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.DIVIDER;

public abstract class AddCommand extends Command {
    protected String userInput;

    public AddCommand(ArrayList<Task> tasks, String userInput) {
        super(tasks);
        this.userInput = userInput;
    }

    public abstract void add() throws IOException;

    public void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + DIVIDER + "\n");
    }
}
