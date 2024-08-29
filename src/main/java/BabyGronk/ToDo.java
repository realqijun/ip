package BabyGronk;

public class ToDo extends Task {
    private final static String TASK_NAME = "[T]";

    ToDo(String task) {
        super(task);
    }

    @Override
    public String   display() {
        return (TASK_NAME + super.toString());
    }
    @Override
    public String toString() {
        return (TASK_NAME + super.toString());
    }
}
