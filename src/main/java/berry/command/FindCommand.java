package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksFound = new TaskList (findTasks(tasks));
        ui.printList(tasksFound.getList(), "Here are the matching tasks in your list:\n");
    }

    private ArrayList<Task> findTasks(TaskList tasks) {
        final ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if(task.getDescription().contains(keyword)){
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
