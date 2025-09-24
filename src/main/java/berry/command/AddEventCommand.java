package berry.command;

import berry.task.Event;
import berry.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.appendToFile;
import static berry.Berry.extractDetails;
import static berry.Berry.printAddTaskMessage;

public class AddEventCommand extends Command {
    private String userInput;

    public AddEventCommand(ArrayList<Task> tasks, String userInput){
        super(tasks);
        this.userInput = userInput;
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
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }
}
