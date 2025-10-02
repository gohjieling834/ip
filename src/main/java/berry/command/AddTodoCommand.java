package berry.command;

import berry.data.BerryException;
import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Todo;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a {@link Todo} to the task list.
 */
public class AddTodoCommand extends Command {

    private final String userInput;

    public AddTodoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to add a {@link Todo} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException If the task description is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (userInput.trim().isEmpty()) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        tasks.addTask(new Todo(userInput));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }

}
