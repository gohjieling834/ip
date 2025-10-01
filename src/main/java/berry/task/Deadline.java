package berry.task;

/**
 * Represents a Deadline task. A {@code Deadline} object corresponds to a task
 * with a deadline specified as a date/time or both.
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileFormat() {
        return "D | " + getStatusIcon() + " | " + getDescription() + " | " + getBy();
    }
}
