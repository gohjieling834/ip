package berry.command;

import berry.data.BerryException;
import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Event;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a {@link Event} to the task list.
 */
public class AddEventCommand extends Command {
    private final String taskDetailsInput;

    /**
     * Creates a new AddEventCommand with the specified task details.
     *
     * @param taskDetailsInput The raw user input string containing the details of the event task.
     */
    public AddEventCommand(String taskDetailsInput) {
        this.taskDetailsInput = taskDetailsInput;
    }

    /**
     * Executes the command to add a {@link Event} task to the {@link TaskList}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     * @throws IOException                    If an error occurs when appending to the file.
     * @throws ArrayIndexOutOfBoundsException If task description or from or to is not specified by user.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.splitDetails(taskDetailsInput);
        checkDetailsFormat(taskDetails);

        int startIndexOfFrom = taskDetails[1].indexOf("from") + 4;  // + 4 because the substring start index should begin after from
        int startIndexOfTo = taskDetails[2].indexOf("to") + 2;  // + 2 because the substring start index should begin after to
        String description = taskDetails[0].trim();
        String from = taskDetails[1].substring(startIndexOfFrom).trim();
        String to = taskDetails[2].substring(startIndexOfTo).trim();

        checkDetails(description, from, to);
        tasks.addTask(new Event(description, from, to));
        storage.appendToFile(tasks.getList());
        ui.printAddTaskMessage(tasks.getList());
    }

    /**
     * Checks if the task details are entered in the correct format.
     *
     * @param details The task details.
     * @throws ArrayIndexOutOfBoundsException If there is any missing details (e.g. description, from, to).
     * @throws BerryException                 If the wrong format is used.
     */
    private void checkDetailsFormat(String[] details) {
        if (details.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Please enter all the event detail (description, from, to). Thank you :)");
        }
        if (!details[1].contains("from") || !details[2].contains("to")) {
            throw new BerryException("Wrong format. Please use the correct format!");
        }
    }

    /**
     * Checks if the task details are empty.
     *
     * @param description The task description.
     * @param from        The task start date.
     * @param to          The task end date.
     * @throws BerryException If any of the details are empty.
     */
    private void checkDetails(String description, String from, String to) {
        if (description.isEmpty()) {
            throw new BerryException("Please enter event description! Thank you :)");
        }
        if (from.isEmpty()) {
            throw new BerryException("Please enter when the event starts! Thank you :)");
        }
        if (to.isEmpty()) {
            throw new BerryException("Please enter when the event ends! Thank you :)");
        }
    }
}
