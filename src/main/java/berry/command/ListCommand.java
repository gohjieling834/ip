package berry.command;

import berry.BerryException;
import berry.task.Task;

import java.util.ArrayList;

import static berry.Berry.DIVIDER;

public class ListCommand extends Command{
    public ListCommand (ArrayList<Task> tasks){
        super(tasks);
    }

    public void print() {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        System.out.println("\n" + DIVIDER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(DIVIDER + "\n");
    }
}
