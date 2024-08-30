package task;

public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String task) {
        taskName = task;
        isDone = false;
    }

    public abstract String display();

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean  isDone() {
        return (isDone);
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return ("[" + status + "] " + taskName);
    }
}