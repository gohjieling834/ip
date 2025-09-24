package berry.command;

import berry.storage.Storage;
import berry.task.Deadline;
import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.extractDetails;

public class AddDeadlineCommand extends Command {
    private final String userInput;
    private final Storage storage;

    public AddDeadlineCommand(ArrayList<Task> tasks, Ui ui, Storage storage, String userInput) {
        super(tasks, ui);
        this.storage = storage;
        this.userInput = userInput;
    }

    public void execute() throws IOException {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();
        tasks.add(new Deadline(description, by));
        storage.appendToFile(tasks);
        ui.printAddTaskMessage(tasks);
    }
}
