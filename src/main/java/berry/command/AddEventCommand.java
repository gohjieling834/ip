package berry.command;

import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Deadline;
import berry.task.Event;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a {@link Event} to the task list.
 */
public class AddEventCommand extends Command {
    private final String taskDetailsInput;

    public AddEventCommand(String taskDetailsInput) {
        this.taskDetailsInput = taskDetailsInput;
    }

    /**
     * Executes the command to add a {@link Event} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException If task description or from or to is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.splitDetails(taskDetailsInput);
        if (taskDetails.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Please enter all the event detail (description, from, to). Thank you :)");
        }
        int startIndexOfFrom = taskDetails[1].indexOf("from") + 4;  // + 4 because the substring start index should begin after from
        int startIndexOfTo = taskDetails[2].indexOf("to") + 2;  // + 2 because the substring start index should begin after to
        String description = taskDetails[0].trim();
        String from = taskDetails[1].substring(startIndexOfFrom).trim();
        String to = taskDetails[2].substring(startIndexOfTo).trim();
        tasks.addTask(new Event(description, from, to));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }
}
