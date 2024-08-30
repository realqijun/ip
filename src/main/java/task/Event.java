package task;

import babygronk.CustomDate;

/**
 * Task child class with event start time and event end time.
 */
public class Event extends Task {
    private final static String TASK_NAME = "[E]";
    private CustomDate from;
    private CustomDate to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = new CustomDate(from);
        this.to = new CustomDate(to);
    }

    @Override
    public String   display() {
        return (TASK_NAME + super.toString() + " (from: " + from + " to: " + to + ")");
    }

    @Override
    public String toString() {
        return (TASK_NAME + super.toString() +
                " (from: " + from.unparseDate() + " to: " + to.unparseDate() + ")");
    }
}
