package berry.command;

import berry.BerryException;
import berry.task.Task;
import berry.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.appendToFile;

public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(ArrayList<Task> tasks, String userInput) {
        super(tasks, userInput);
    }

    public void add() throws IOException {
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description));
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }

}
