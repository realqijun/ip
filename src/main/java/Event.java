public class Event extends Task {
    private final static String TASK_NAME = "[E]";
    private String from;
    private String to;

    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return (TASK_NAME + super.toString() + " (from: " + from + " to: " + to + ")");
    }
}
