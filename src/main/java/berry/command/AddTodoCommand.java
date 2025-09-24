package berry.command;

import berry.BerryException;
import berry.storage.Storage;
import berry.task.Task;
import berry.task.Todo;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class AddTodoCommand extends Command {
    private final String userInput;
    private final Storage storage;

    public AddTodoCommand(ArrayList<Task> tasks, Ui ui, Storage storage, String userInput) {
        super(tasks, ui);
        this.userInput = userInput;
        this.storage = storage;
    }

    public void execute() throws IOException {
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description));
        storage.appendToFile(tasks);
        ui.printAddTaskMessage(tasks);
    }

}
