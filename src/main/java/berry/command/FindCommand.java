package berry.command;

import berry.data.TaskList;
import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command that finds the tasks in {@link TaskList} that matches the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * <p>
     * This method creates a new {@link TaskList} to store the tasks found and
     * prints the list through the {@link Ui}.
     *
     * @param tasks   List that holds all current tasks.
     * @param ui      Ui instance used to display messages to the user.
     * @param storage Storage instance used to update berry.txt.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksFound = new TaskList(findTasks(tasks));
        ui.printList(tasksFound.getList(), "Here are the matching tasks in your list:\n");
    }

    /**
     * Finds the tasks that matches the keyword and returns an ArrayList containing the found tasks.
     *
     * @param tasks List that holds all current tasks.
     * @return an ArrayList that contains all found tasks.
     */
    private ArrayList<Task> findTasks(TaskList tasks) {
        final ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
