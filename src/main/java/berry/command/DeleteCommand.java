package berry.command;

import berry.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.DIVIDER;
import static berry.Berry.printErrorMessage;
import static berry.Berry.updateFile;

public class DeleteCommand extends Command {
    private String userInput;
    private String userCommand;

    public DeleteCommand(ArrayList<Task> tasks, String userCommand, String userInput) {
        super(tasks);
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void execute() {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            System.out.println("\n" + DIVIDER + "\n" + "Okay, I've removed this task:\n  "
                    + tasks.remove(taskNumber) + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n"
                    + DIVIDER + "\n");
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }
}
