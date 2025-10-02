package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that marks or unmarks a task in the list.
 */
public class MarkCommand extends Command {
    private final String userCommand;
    private final String userInput;

    public MarkCommand(String userCommand, String userInput) {
        this.userCommand = userCommand;
        this.userInput = userInput;
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
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            boolean isUnmarkCommand = userInput.contains("un");
            boolean isDone = tasks.getTask(taskNumber).isDone();
            if (isUnmarkCommand && isDone) {
                tasks.getTask(taskNumber).markAsUndone();
                ui.printMarkTaskMessage(tasks.getTask(taskNumber), "Okay, I've marked this task as not done yet:\n  ");
            } else if (isUnmarkCommand && !isDone) {
                ui.printMarkTaskMessage(tasks.getTask(taskNumber), "This task is already marked as not done:\n  ");
            } else if (!isUnmarkCommand && isDone) {
                ui.printMarkTaskMessage(tasks.getTask(taskNumber), "This task is already marked as done:\n  ");
            } else {
                tasks.getTask(taskNumber).markAsDone();
                ui.printMarkTaskMessage(tasks.getTask(taskNumber), "Nice! I've marked this task as done:\n  ");
            }
            storage.updateFile(taskNumber, userCommand, tasks.getList());
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to mark/unmark. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
