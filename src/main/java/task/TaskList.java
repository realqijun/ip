package task;

import babygronk.BabyGronk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private List<Task> tasks;

    public TaskList(Stream<String> tasks) {
        this.tasks = new ArrayList<>(100);
        if (tasks != null) {
            tasks.forEach((task) -> {
                this.tasks.add(BabyGronk.initTasks(task));
            });
        }
    }

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

    public String add(Task task) {
        tasks.add(task);
        return ("added task: " + tasks.get(tasks.size() - 1).toString() + "\n" +
                tasks.size() + " task(s) in the list rn\n");
    }

    public String   markTask(String[] input, boolean status) {
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
                builder.append("Let's go! +100 aura points!\n").append(task).append("\n");
            } else {
                builder.append("Bruh, you're cooked, -500 aura points\n").append(task).append("\n");
            }
        }
        return (builder.toString());
    }

    public String   find(String needle) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.display().contains(needle)) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            return ("No matches to " + needle + " found\n");
        }

        StringBuilder builder = new StringBuilder("I found some matches! W rizz\n");
        for (int i = 0; i < matches.size(); i++) {
            Task task = matches.get(i);
            builder.append(i + 1).append(".").append(task.display()).append("\n");
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
            builder.append(i + 1).append(".").append(tasks.get(i).display()).append("\n");
        }
        return (builder.toString());
    }
}
