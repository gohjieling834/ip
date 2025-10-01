package berry.data;

import berry.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@link Task} to the list.
     *
     * @param task The task to be added into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * removes a {@link Task} from the list.
     *
     * @param taskNumber The number specifying which task to remove.
     */
    public Task removeTask(int taskNumber) {
        return tasks.remove(taskNumber);
    }

    /**
     * Returns the number of tasks currently stored in the list.
     *
     * @return the total number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a {@link Task}.
     *
     * @param taskNumber The number specifying which task from the list to return.
     * @return the task specified.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns the list.
     *
     * @return the list of {@link Task} objects.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty. Else return false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
