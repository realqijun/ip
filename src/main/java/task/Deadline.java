package task;

import babygronk.CustomDate;

public class Deadline extends Task {
    private final static String TASK_TYPE = "[D]";
    private CustomDate deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = new CustomDate(deadline);
    }

    @Override
    public String   display() {
        return (TASK_TYPE + super.toString() + " (by: " + deadline + ")");
    }

    @Override
    public String   toString() {
        return (TASK_TYPE + super.toString() + " (by: " + deadline.unparseDate() + ")");
    }
}
