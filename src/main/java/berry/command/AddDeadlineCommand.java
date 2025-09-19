package berry.command;

import berry.task.Deadline;
import berry.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static berry.Berry.appendToFile;
import static berry.Berry.extractDetails;

public class AddDeadlineCommand extends AddCommand{

    public AddDeadlineCommand(ArrayList<Task> tasks, String userInput) {
        super(tasks, userInput);
    }

    @Override
    public void add() throws IOException {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();
        tasks.add(new Deadline(description, by));
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }
}
