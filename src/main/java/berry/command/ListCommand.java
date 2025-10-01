package berry.command;

import berry.data.BerryException;
import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

/**
 * Represents a command that prints all the tasks in the {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * <p>
     * This method throws a BerryException if the task list is empty. Else it will print
     * the list through the {@link Ui}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        ui.printList(tasks.getList(), "");
    }
}
