package berry.command;

import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.updateFile;

public class DeleteCommand extends Command {
    private final String userInput;
    private final String userCommand;

    public DeleteCommand(ArrayList<Task> tasks, Ui ui, String userCommand, String userInput) {
        super(tasks, ui);
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void execute() {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            ui.printDeleteTaskMessage(tasks.remove(taskNumber), tasks.size());
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
