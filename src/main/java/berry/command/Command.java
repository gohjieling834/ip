package berry.command;

import java.io.IOException;
import java.util.ArrayList;

import berry.task.Task;
import berry.ui.Ui;

public abstract class Command {
    protected ArrayList<Task> tasks;
    protected Ui ui;

    public Command(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public abstract void execute() throws IOException;
}
