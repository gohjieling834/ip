package berry.command;

import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class DeleteCommand extends Command {
    private final String userInput;
    private final String userCommand;
    private final Storage storage;

    public DeleteCommand(ArrayList<Task> tasks, Ui ui, Storage storage, String userCommand, String userInput) {
        super(tasks, ui);
        this.userCommand = userCommand;
        this.userInput = userInput;
        this.storage = storage;
    }

    public void execute() {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            ui.printDeleteTaskMessage(tasks.remove(taskNumber), tasks.size());
            storage.updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
