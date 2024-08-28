public class Deadline extends Task {
    private final static String TASK_NAME = "[D]";
    private CustomDate deadlineDate;

    Deadline(String task, String deadline) {
        super(task);
        this.deadlineDate = new CustomDate(deadline);
    }

    @Override
    public String   display() {
        return (TASK_NAME + super.toString() + " (by: " + deadlineDate + ")");
    }

    @Override
    public String   toString() {
        return (TASK_NAME + super.toString() + " (by: " + deadlineDate.unparseDate() + ")");
    }
}
