package task;

/**
 * Task child class identical to task.
 */
public class ToDo extends Task {
    private final static String TASK_TYPE = "[T]";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String   display() {
        return (TASK_TYPE + super.toString());
    }
    @Override
    public String toString() {
        return (TASK_TYPE + super.toString());
    }
}
