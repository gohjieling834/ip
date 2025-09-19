package berry.command;

import java.util.ArrayList;
import berry.task.Task;

public abstract class Command {
    protected ArrayList<Task> tasks;

    public Command (ArrayList<Task> tasks){
        this.tasks = tasks;
    }
}
