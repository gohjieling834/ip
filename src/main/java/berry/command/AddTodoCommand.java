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

    private final String taskDetailsInput;

    /**
     * Creates a new AddTodoCommand with the specified task details.
     *
     * @param taskDetailsInput The raw user input string containing the details of the Todo task.
     */
    public AddTodoCommand(String taskDetailsInput) {
        this.taskDetailsInput = taskDetailsInput;
    }

    /**
     * Executes the command to add a {@link Todo} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException If an error occurs when appending to the file.
     * @throws BerryException If the task description is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskDetailsInput.trim().isEmpty()) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        tasks.addTask(new Todo(taskDetailsInput));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }

}
