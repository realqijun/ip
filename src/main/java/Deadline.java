public class Deadline extends Task {
    private final static String TASK_NAME = "[D]";
    private String deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return (TASK_NAME + super.toString() + " (by: " + deadline + ")");
    }
}
