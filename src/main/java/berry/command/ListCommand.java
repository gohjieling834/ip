package berry.command;

import berry.data.BerryException;
import berry.data.TaskList;
import berry.storage.Storage;
import berry.ui.Ui;


public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        ui.printList(tasks.getList(), "");
    }
}
