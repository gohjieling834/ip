package berry.command;

import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Deadline;
import berry.ui.Ui;

import java.io.IOException;


public class AddDeadlineCommand extends Command {
    private final String userInput;

    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] taskDetails = Parser.extractDetails(userInput);
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
