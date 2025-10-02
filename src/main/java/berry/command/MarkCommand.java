package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that marks or unmarks a task in the list.
 */
public class MarkCommand extends Command {
    private static final String COMMAND = "mark";
    private final String number;
    private final boolean isMarkCommand;

    public MarkCommand(String number, boolean isMarkCommand) {
        this.number = number;
        this.isMarkCommand = isMarkCommand;
    }

    /**
     * Executes mark and unmark command.
     * <p>
     * This method marks or unmarks the task specified depending on the command
     * and updates berry.txt accordingly. If the task number specified by user
     * is invalid (e.g. non-numeric or out-of-range task number), an error message
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
            Task task = tasks.getTask(taskNumber);
            if (!isMarkCommand) {
                unmarkTask(task, ui);
            } else {
                markTask(task, ui);
            }
            storage.updateFile(taskNumber, COMMAND, tasks.getList());
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to mark/unmark. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Marks a task as done if it is not already done.
     *
     * @param task The task to be marked as done.
     * @param ui   The UI handler to print messages to the user.
     */
    private void markTask(Task task, Ui ui) {
        if (task.isDone()) {
            ui.printMarkTaskMessage(task, "This task is already marked as done:\n  ");
        } else {
            task.markAsDone();
            ui.printMarkTaskMessage(task, "Nice! I've marked this task as done:\n  ");
        }
    }

    /**
     * Marks a task as not done if it is currently done.
     *
     * @param task The task to be marked as not done.
     * @param ui   The UI handler to print messages to the user.
     */
    private void unmarkTask(Task task, Ui ui) {
        if (!task.isDone()) {
            ui.printMarkTaskMessage(task, "This task is already marked as not done:\n  ");
        } else {
            task.markAsUndone();
            ui.printMarkTaskMessage(task, "Okay, I've marked this task as not done yet:\n  ");
        }
    }
}
