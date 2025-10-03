package berry.command;

import berry.data.BerryException;
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

    /**
     * Creates a new AddDeadlineCommand with the specified task details.
     *
     * @param taskDetailsInput The raw user input string containing the details of the deadline task.
     */
    public AddDeadlineCommand(String taskDetailsInput) {
        this.taskDetailsInput = taskDetailsInput;
    }

    /**
     * Executes the command to add a {@link Deadline} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException                    If an error occurs when appending to the file.
     * @throws ArrayIndexOutOfBoundsException If either task description or by is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.splitDetails(taskDetailsInput);
        checkDetailsFormat(taskDetails);

        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();

        checkDetails(description, by);
        tasks.addTask(new Deadline(description, by));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }

    /**
     * Checks if the task details are entered in the correct format.
     *
     * @param details The task details.
     * @throws ArrayIndexOutOfBoundsException If there is any missing details (e.g. description, by).
     * @throws BerryException                 If the wrong format is used.
     */
    private void checkDetailsFormat(String[] details) {
        if (details.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        if (!details[1].contains("by")) {
            throw new BerryException("Wrong format. Please use the correct format!");
        }
    }

    /**
     * Checks if the task details are empty.
     *
     * @param description The task description.
     * @param by          The task due date.
     * @throws BerryException If any of the details are empty.
     */
    private void checkDetails(String description, String by) {
        if (description.isEmpty()) {
            throw new BerryException("Please enter the task description. Thank you :)");
        }
        if (by.isEmpty()) {
            throw new BerryException("Please enter the task due date. Thank you :)");
        }
    }
}
