package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
