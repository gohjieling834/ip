package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String COMMAND = "delete";
    private final String number;

    public DeleteCommand(String number) {
        this.number = number;

    }

    /**
     * Executes the command to delete a task from the {@link TaskList}.
     * <p>
     * This method parses the user input to identify the task number, removes
     * the corresponding task from the list, updates berry.txt, and displays
     * a confirmation message to the user. If the user input is invalid
     * (e.g. non-numeric or out-of-range task number), an error message
     * is displayed instead. Any I/O errors when updating storage will also
     * display an error message.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            int taskNumber = Integer.parseInt(number.trim()) - 1;
            ui.printDeleteTaskMessage(tasks.removeTask(taskNumber), tasks.getSize());
            storage.updateFile(taskNumber, COMMAND, tasks.getList());
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
