package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final String userCommand;
    private final String userInput;

    public MarkCommand(String userCommand, String userInput) {
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            if (userInput.contains("un")) {
                tasks.getTask(taskNumber).markAsUndone();
                ui.printMarkTaskMessage(tasks.getTask(taskNumber), "Okay, I've marked this task as not done yet:\n  ");
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
