package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stores a list of tasks in an ArrayList data structure.
 */
public class TaskList {
    private final List<Task> tasks;
    private static final int INITIAL_SIZE = 100;

    /**
     * Main constructor for taskList. Takes in a Stream of strings each representing a task.
     * Invokes BabyGronk::initTasks for each string and adds them to the arraylist.
     *
     * @param tasks Steam of tasks.
     */
    public TaskList(Stream<String> tasks) {
        this.tasks = new ArrayList<>(INITIAL_SIZE);
        if (tasks != null) {
            tasks.forEach((task) -> this.tasks.add(initTasks(task)));
        }
    }

    private static Task initTasks(String input) {
        String[] args = input.split("] ");
        if (args.length != 2) {
            return (invalidTask(input));
        }

        char taskType = args[0].charAt(1);
        boolean isDone = args[0].charAt(4) == 'X';

        switch (taskType) {
        case 'T':
            return (initTodoTask(args[1], isDone));
        case 'D':
            return (initDeadlineTask(args[1], isDone));
        case 'E':
            return (initEventTask(args[1], isDone));
        default:
            return (invalidTask(input));
        }
    }

    private static Task initTodoTask(String arg, boolean isDone) {
        ToDo todo = new ToDo(arg);
        todo.setDone(isDone);
        return (todo);
    }

    private static Task initDeadlineTask(String arg, boolean isDone) {
        String[] deadlineArgs = arg.split(" \\(");
        if (deadlineArgs.length != 2) {
            return (invalidTask(arg));
        }

        Deadline deadline = new Deadline(deadlineArgs[0], deadlineArgs[1].substring(4, deadlineArgs[1].length() - 1));
        deadline.setDone(isDone);
        return (deadline);
    }

    private static Task initEventTask(String arg, boolean isDone) {
        String[] eventArgs = arg.split(" \\(");
        if (eventArgs.length != 2) {
            return (invalidTask(arg));
        }

        String[] eventDetails = eventArgs[1].split(" to: ");
        if (eventDetails.length != 2) {
            return (invalidTask(arg));
        }

        Event event = new Event(eventArgs[0], eventDetails[0].substring(6),
                eventDetails[1].substring(0, eventDetails[1].length() - 1));
        event.setDone(isDone);
        return (event);
    }

    private static Task invalidTask(String input) {
        System.out.println("Line: " + input + " has invalid format type, data cannot be loaded");
        return (null);
    }

    /**
     * Deletes tasks given in input.
     *
     * @param input Array of strings, each string representing a task.
     * @return Status of deletion.
     */
    public String delete(String[] input) {
        StringBuilder builder = new StringBuilder();
        for (String s : input) {
            int toDelete = Integer.parseInt(s);
            if (toDelete < 1 || toDelete > tasks.size()) {
                if (tasks.isEmpty()) {
                    return ("Ohio level brainrot detected, you haven't even added anything to the list\n");
                } else {
                    return ("Please pick within the range 1 - " + tasks.size() + "\n");
                }
            }
            Task deleted = tasks.remove(toDelete - 1);
            builder.append(deleted).append("\n has been ejected (they were the impostor)\n")
                    .append(tasks.size()).append(" tasks remain\n");
        }
        return (builder.toString());
    }

    /**
     * Adds a task to the list.
     *
     * @param task A task.
     * @return The status string.
     */
    public String add(Task task) {
        tasks.add(task);
        return ("added task: " + tasks.get(tasks.size() - 1).toString() + "\n"
                + tasks.size() + " task(s) in the list rn\n");
    }

    /**
     * Marks the tasks as done/not done. Stops marking tasks if one of them
     * in the list is already marked done/undone.
     *
     * @param input The list of tasks to mark as done/undone.
     * @param status True if to mark as done, and False to unmark.
     * @return The status string.
     */
    public String markTask(String[] input, boolean status) {
        StringBuilder builder = new StringBuilder();
        for (String s : input) {
            int toMark = Integer.parseInt(s);
            if (toMark > tasks.size() || toMark < 1) {
                return ("You got no rizz, try learning how to count (index " + toMark + " out of range)\n");
            }

            Task task = tasks.get(toMark - 1);
            if (status == task.isDone()) {
                return ("Task " + toMark + " is already " + (status ? "marked" : "unmarked") + "\n");
            }
            task.setDone(status);
            if (status) {
                builder.append("Let's go! +100 aura points!\n").append(task).append(" marked as done\n");
            } else {
                builder.append("Bruh, you're cooked, -500 aura points\n").append(task).append(" has been unmarked\n");
            }
        }
        return (builder.toString());
    }

    /**
     * Finds the task that matches needle string.
     *
     * @param needle The keyword of string to find.
     * @return If found, the description of every task that matches, else, "No Matches".
     */
    public String find(String needle) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(needle)) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            return ("No matches to " + needle + " found\n");
        }

        StringBuilder builder = new StringBuilder("I found some matches! W rizz\n");
        for (int i = 0; i < matches.size(); i++) {
            Task task = matches.get(i);
            builder.append(i + 1).append(".").append(task.toString()).append("\n");
        }
        return (builder.toString());
    }

    public List<Task> getTasks() {
        return (tasks);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return (builder.toString());
    }
}
