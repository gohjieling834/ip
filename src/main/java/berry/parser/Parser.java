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

/**
 * Handles the parsing of user input into structured commands and task details.
 */
public class Parser {
    // extractCommand method inspired by
    // https://github.com/nus-cs2113-AY2526S1/personbook/blob/master/src/main/java/seedu/personbook/parser/Parser.java

    /**
     * Returns the command specified by the user.
     * <p>
     * This method instantiates the appropriate {@link Command} subclass.
     * If the command is invalid, an error message is displayed.
     *
     * @param userInput The full string entered by the user.
     * @return a Command object that represents the user's command.
     */
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

    /**
     * Extracts the task details from the user input.
     *
     * @param taskDetailsInput The part of the user's input string that contains only the task details.
     * @return an array of strings representing the extracted task details.
     */
    public static String[] splitDetails(String taskDetailsInput) {
        return taskDetailsInput.split("/");
    }
}
