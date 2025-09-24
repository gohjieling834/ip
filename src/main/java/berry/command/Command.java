package berry.command;

import java.io.IOException;
import java.util.ArrayList;

import berry.task.Task;

public abstract class Command {
    protected ArrayList<Task> tasks;

    public Command(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public abstract void execute() throws IOException;
}
