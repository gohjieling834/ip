package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

/**
 * Represents an abstract user command in the Berry chatbot.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     * Subclasses should define the specific behaviour of the command, such as adding,
     * deleting, or marking tasks.
     *
     * @param tasks   The task list to be modified or queried.
     * @param ui      The user interface used to display messages.
     * @param storage The storage handler responsible for saving tasks.
     * @throws IOException If an error occurs while accessing or modifying storage.
     */
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
