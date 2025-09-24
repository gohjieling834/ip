package berry.command;

import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Event;
import berry.task.Task;
import berry.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class AddEventCommand extends Command {
    private final String userInput;

    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.extractDetails(userInput);
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
