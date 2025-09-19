package berry.command;

import berry.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.DIVIDER;
import static berry.Berry.printErrorMessage;
import static berry.Berry.updateFile;

public class MarkCommand extends Command {
    private String userCommand;
    private String userInput;

    public MarkCommand(ArrayList<Task> tasks, String userCommand, String userInput) {
        super(tasks);
        this.userCommand = userCommand;
        this.userInput = userInput;
    }

    public void toggleTaskStatus() {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            if (userInput.contains("un")) {
                tasks.get(taskNumber).markAsUndone();
                System.out.println("\n" + DIVIDER + "\n" + "Okay, I've marked this task as not done yet:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            } else {
                tasks.get(taskNumber).markAsDone();
                System.out.println("\n" + DIVIDER + "\n" + "Nice! I've marked this task as done:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            }
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            printErrorMessage("Sorry, I don't know which task to mark/unmark. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }
}
