package berry.parser;

import berry.command.AddDeadlineCommand;
import berry.command.AddEventCommand;
import berry.command.AddTodoCommand;
import berry.command.Command;
import berry.command.DeleteCommand;
import berry.command.ExitCommand;
import berry.command.FindCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.data.BerryException;

public class Parser {
    // extractCommand method inspired by
    // https://github.com/nus-cs2113-AY2526S1/personbook/blob/master/src/main/java/seedu/personbook/parser/Parser.java

    public static Command extractCommand(String userInput) {
        String[] words = userInput.split(" ", 2);   // split the input into command and details
        switch (words[0].trim()) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddTodoCommand(words[1]);
        case "deadline":
            return new AddDeadlineCommand(words[1]);
        case "event":
            return new AddEventCommand(words[1]);
        case "unmark":
            // fall through
        case "mark":
            return new MarkCommand("mark", userInput);
        case "delete":
            return new DeleteCommand("delete", userInput);
        case "find":
            return new FindCommand(words[1]);
        case "bye":
            return new ExitCommand();
        default:
            throw new BerryException("Sorry, I'm not sure what you want me to do ._.");
        }
    }

    public static String[] extractDetails(String userInput) {
        return userInput.split("/");
    }
}
