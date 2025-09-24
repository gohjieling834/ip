package berry.command;

import berry.BerryException;
import berry.task.Task;
import berry.ui.Ui;

import java.util.ArrayList;


public class ListCommand extends Command{
    public ListCommand (ArrayList<Task> tasks, Ui ui){
        super(tasks, ui);
    }

    public void execute() {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        ui.printList(tasks);
    }
}
