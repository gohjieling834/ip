package berry.data;

import berry.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int taskNumber) {
        return tasks.remove(taskNumber);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
