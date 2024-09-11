package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stores a list of tasks in an ArrayList data structure.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Main constructor for taskList. Takes in a Stream of strings each representing a task.
     * Invokes BabyGronk::initTasks for each string and adds them to the arraylist.
     *
     * @param tasks Steam of tasks.
     */
    public TaskList(Stream<String> tasks) {
        this.tasks = new ArrayList<>(100);
        if (tasks != null) {
            tasks.forEach((task) -> {
                this.tasks.add(initTasks(task));
            });
        }
    }

    private static Task initTasks(String input) {
        String[] args = input.split("] ");
        if (args[0].charAt(1) == 'T') {
            ToDo todo = new ToDo(args[1]);
            if (args[0].charAt(4) == 'X') {
                todo.setDone(true);
            }
            return (todo);
        } else if (args[0].charAt(1) == 'D') {
            String[] args2 = args[1].split(" \\(");
            Deadline deadline = new Deadline(args2[0], args2[1].substring(4, args2[1].length() - 1));
            if (args[0].charAt(4) == 'X') {
                deadline.setDone(true);
            }
            return (deadline);
        } else if (args[0].charAt(1) == 'E') {
            String[] args2 = args[1].split(" \\(");
            String[] args3 = args2[1].split(" to: ");
            Event event = new Event(args2[0], args3[0].substring(6), args3[1].substring(0, args3[1].length() - 1));
            if (args[0].charAt(4) == 'X') {
                event.setDone(true);
            }
            return (event);
        } else {
            System.out.println("Line: " + input + " has invalid format type, data cannot be loaded");
            return (null);
        }
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

    public Stream<Task> getTasks() {
        return (tasks.stream());
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
