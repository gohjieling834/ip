package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents an abstract user command in the Berry chatbot.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if it's exit command. Else false.
     */
    public boolean isExit() {
        return false;
    }
}
