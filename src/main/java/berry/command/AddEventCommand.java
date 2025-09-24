package berry.command;

import berry.storage.Storage;
import berry.task.Event;
import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.extractDetails;

public class AddEventCommand extends Command {
    private final String userInput;
    private final Storage storage;

    public AddEventCommand(ArrayList<Task> tasks, Ui ui, Storage storage, String userInput){
        super(tasks, ui);
        this.userInput = userInput;
        this.storage = storage;
    }

    public void execute() throws IOException {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Please enter all the event detail (description, from, to). Thank you :)");
        }
        int startIndexOfFrom = taskDetails[1].indexOf("from") + 4;  // + 4 because the substring start index should begin after from
        int startIndexOfTo = taskDetails[2].indexOf("to") + 2;  // + 2 because the substring start index should begin after to
        String description = taskDetails[0].trim();
        String from = taskDetails[1].substring(startIndexOfFrom).trim();
        String to = taskDetails[2].substring(startIndexOfTo).trim();
        tasks.add(new Event(description, from, to));
        storage.appendToFile(tasks);
        ui.printAddTaskMessage(tasks);
    }
}
