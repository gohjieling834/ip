package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

/**
 * Represents a command that exits from the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command and prints the bye message through the {@link Ui}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true, because this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
