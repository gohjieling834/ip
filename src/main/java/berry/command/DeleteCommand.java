package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;


public class DeleteCommand extends Command {
    private final String userInput;
    private final String userCommand;

    public DeleteCommand(String userCommand, String userInput) {
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            ui.printDeleteTaskMessage(tasks.removeTask(taskNumber), tasks.getSize());
            storage.updateFile(taskNumber, userCommand, tasks.getList());
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
