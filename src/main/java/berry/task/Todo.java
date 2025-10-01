package berry.task;

/**
 * Represents a task to complete.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileFormat() {
        return "T | " + getStatusIcon() + " | " + getDescription();
    }
}
