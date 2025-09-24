package berry.parser;

import berry.command.AddDeadlineCommand;
import berry.command.AddEventCommand;
import berry.command.AddTodoCommand;
import berry.command.Command;
import berry.command.DeleteCommand;
import berry.command.ExitCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.data.BerryException;

public class Parser {
    public static Command extractCommand(String userInput) {
        if (userInput.contains("list")) {
            return new ListCommand();
        } else if (userInput.contains("todo")) {
            return new AddTodoCommand(userInput);
        } else if (userInput.contains("deadline")) {
            return new AddDeadlineCommand(userInput);
        } else if (userInput.contains("event")) {
            return new AddEventCommand(userInput);
        } else if (userInput.contains("mark")) {
            return new MarkCommand("mark", userInput);
        } else if (userInput.contains("delete")) {
            return new DeleteCommand("delete", userInput);
        } else if (userInput.contains("bye")) {
            return new ExitCommand();
        } else {
            throw new BerryException("Sorry, I'm not sure what you want me to do ._.");
        }
    }

    public static String[] extractDetails(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        String task = userInput.substring(dividerPosition + 1);
        return task.split("/");
    }
}
