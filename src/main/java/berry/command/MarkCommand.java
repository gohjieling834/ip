package berry.command;

import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.updateFile;

public class MarkCommand extends Command {
    private final String userCommand;
    private final String userInput;

    public MarkCommand(ArrayList<Task> tasks, Ui ui, String userCommand, String userInput) {
        super(tasks, ui);
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void execute() {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            if (userInput.contains("un")) {
                tasks.get(taskNumber).markAsUndone();
                ui.printMarkTaskMessage(tasks.get(taskNumber), "Okay, I've marked this task as not done yet:\n  ");
            } else {
                tasks.get(taskNumber).markAsDone();
                ui.printMarkTaskMessage(tasks.get(taskNumber), "Nice! I've marked this task as done:\n  ");
            }
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, I don't know which task to mark/unmark. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
