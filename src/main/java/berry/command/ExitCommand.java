package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.util.ArrayList;

public class ExitCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printByeMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
