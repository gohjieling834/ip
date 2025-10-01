package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
