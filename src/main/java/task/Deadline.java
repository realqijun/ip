package task;

import babygronk.CustomDate;

/**
 * Task child class with deadline delimited by /by
 */
public class Deadline extends Task {
    private final static String TASK_NAME = "[D]";
    private CustomDate deadlineDate;

    public Deadline(String task, String deadline) {
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
