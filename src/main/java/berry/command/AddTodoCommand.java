package berry.command;

import berry.data.BerryException;
import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Todo;
import berry.ui.Ui;

import java.io.IOException;


public class AddTodoCommand extends Command {

    private final String userInput;

    public AddTodoCommand(String userInput) {
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.addTask(new Todo(description));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }

}
