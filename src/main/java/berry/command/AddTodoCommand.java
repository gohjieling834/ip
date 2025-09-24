package berry.command;

import berry.BerryException;
import berry.task.Task;
import berry.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.appendToFile;
import static berry.Berry.printAddTaskMessage;

public class AddTodoCommand extends Command {
    private String userInput;

    public AddTodoCommand(ArrayList<Task> tasks, String userInput) {
        super(tasks);
        this.userInput = userInput;
    }

    public void execute() throws IOException {
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description));
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }

}
