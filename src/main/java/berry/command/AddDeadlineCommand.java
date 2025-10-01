package berry.command;

import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Deadline;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a {@link Deadline} to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String taskDetailsInput;

    public AddDeadlineCommand(String taskDetailsInput) {
        this.taskDetailsInput = taskDetailsInput;
    }

    /**
     * Executes the command to add a {@link Deadline} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException If either task description or by is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.splitDetails(taskDetailsInput);
        if (taskDetails.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();
        tasks.addTask(new Deadline(description, by));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }
}
