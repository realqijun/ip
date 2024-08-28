import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BabyGronk {
    private static final String  DATA = "./data/BabyGronk.txt";
    private final static String SEPARATOR =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";
    private static List<Task>  tasks = new ArrayList<>();

    private static void logOff() {
        String goodByeMessage = SEPARATOR +
                """
                Going back to my skibidi toilet.
                """ +
                SEPARATOR;
        System.out.println(goodByeMessage);
        saveData();
        System.exit(0);
    }

    private static void greet() {
        String welcomeMessage = SEPARATOR +
                """ 
                What's up ohio? I'm BabyGronk, let's see how sigma you are
                Gyatt Damn
                """ +
                SEPARATOR;
        System.out.println(welcomeMessage);
    }

    private static String   handleInput(String input) throws EmptyInputException {
        if (input == null || input.isEmpty()) {
            throw new EmptyInputException ("What bro? You're too fanum taxed\n");
        }
        if (input.equals("bye")) {
            logOff();
        }
        if (input.equals("list")) {
            return (listTasks());
        }
        if (input.startsWith("unmark") || input.startsWith("mark")) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                return ("Usage: [mark/unmark] [index]\n");
            }
            if (input.startsWith("unmark")) {
                return (markTask(args[1], false));
            } else {
                return (markTask(args[1], true));
            }
        }
        if (input.startsWith("delete")) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                return ("How can you not delete properly? (delete [index])\n");
            }
            return (deleteTask(args[1]));
        }
        return (addTask(input));
    }

    private static String deleteTask(String input) {
        int toDelete = Integer.parseInt(input);
        if (toDelete < 1 || toDelete > tasks.size()) {
            if (tasks.isEmpty()) {
                return ("Ohio level brainrot detected, you haven't even added anything to the list\n");
            } else {
                return ("Please pick within the range 1 - " + tasks.size() + "\n");
            }
        }
        Task deleted = tasks.remove(toDelete - 1);
        return (deleted + "\n has been ejected (they were the impostor)\n" + tasks.size() + " tasks remain\n");
    }

    private static String addTask(String input) {
        String[] args = input.split(" ", 2);
        if (args.length < 2) {
            return ("No grimace shake for you ([task_type] [task_name] [args])\n");
        }
        if (args[0].startsWith("todo")) {
            tasks.add(new ToDo(args[1]));
        } else if (args[0].startsWith("deadline")) {
            String[] deadlineArgs = args[1].split(" /by ",2);
            if (deadlineArgs.length < 2) {
                return ("no deadline!\n");
            }
            tasks.add(new Deadline(deadlineArgs[0], deadlineArgs[1]));
        } else if (args[0].equals("event")) {
            String[] eventFromArgs = args[1].split(" /from ",2);
            if (eventFromArgs.length < 2) {
                return ("no event from!\n");
            }
            String[] eventToArgs = eventFromArgs[1].split(" /to ",2);
            if (eventToArgs.length < 2) {
                return ("no event to to!\n");
            }
            tasks.add(new Event(eventFromArgs[0], eventToArgs[0], eventToArgs[1]));
        } else {
            return ("invalid task type (only todo, deadline, event allowed)\n");
        }
        return ("added task: " + tasks.get(tasks.size() - 1).toString() + "\n" +
                tasks.size() + " task(s) in the list rn\n");
    }

    private static String   listTasks() {
        StringBuilder hist = new StringBuilder("To do list 📋☑️:\n");
        for (int i = 0; i < tasks.size(); i++) {
            hist.append(i + 1).append(".").append(tasks.get(i).display()).append("\n");
        }
        return hist.toString();
    }

    private static String   markTask(String input, boolean status) {
        if (input == null || input.isEmpty()) {
            return ("Are you acoustic? Give me a number! (mark [index])\n");
        }
        int toMark = Integer.parseInt(input);
        if (toMark > tasks.size() || toMark < 1) {
            return ("You got no rizz, try learning how to count (index out of range)\n");
        }
        Task task = tasks.get(toMark - 1);
        if (status == task.isDone()) {
            return ("Task is already " + (status ? "marked" : "unmarked") + "\n");
        }
        task.setDone(status);
        if (status) {
            return ("Let's go! +100 aura points!\n" + task + "\n");
        } else {
            return ("Bruh, you're cooked, -500 aura points\n" + task + "\n");
        }
    }

    private static void  initDataFile() {
        File file = new File(DATA);
        if (!file.exists()) {
            boolean directoryCreated = file.getParentFile().mkdirs();
            if (!directoryCreated) {
                System.out.println("Directory was not created");
            }
            boolean fileCreated = false;
            try {
                fileCreated = file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
            if (!fileCreated) {
                System.out.println("File was not created");
            }
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(DATA);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.lines().forEach(BabyGronk::initTasks);
    }

    private static void initTasks(String input) {
        String[] args = input.split("] ");
        if (args[0].charAt(1) == 'T') {
            tasks.add(new ToDo(args[1]));
            if (args[0].charAt(4) == 'X') {
                tasks.get(tasks.size() - 1).setDone(true);
            }
        } else if (args[0].charAt(1) == 'D') {
            String[] args2 = args[1].split(" \\(");
            tasks.add(new Deadline(args2[0], args2[1].substring(4, args2[1].length() - 1)));
            if (args[0].charAt(4) == 'X') {
                tasks.get(tasks.size() - 1).setDone(true);
            }
        } else if (args[0].charAt(1) == 'E') {
            String[] args2 = args[1].split(" \\(");
            String[] args3 = args2[1].split(" to: ");
            tasks.add(new Event(args2[0], args3[0].substring(6), args3[1].substring(0, args3[1].length() - 1)));
            if (args[0].charAt(4) == 'X') {
                tasks.get(tasks.size() - 1).setDone(true);
            }
        } else {
            System.out.println("Line: " + input + " has invalid format type, data cannot be loaded");
        }
    }

    private static void saveData() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(DATA));
            for (Task t: tasks) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public static void  main(String[] args) {
        String logo = """
                ⣠⣀⣤⣶⣶⣶⣶⣤⣤⣤⣤⣄⡀⠀⠀⠀⢀⣀⣀⣤⣤⣤⣶⣶⣶⣶⣬⣒⢦⡀
                ⡾⠛⠉⠉⢀⣀⣈⣉⣉⣉⣻⠛⠁⠀⠀⠀⠀⠙⢛⣛⣉⣉⣉⣉⣀⠀⠉⠙⠻⢮
                ⠀⠀⣀⠴⢲⣶⣶⣶⠶⡦⠄⢷⡄⠀⠀⠀⠀⣼⠃⠴⡶⢶⣶⣶⢶⠲⢤⡀⠀⠀
                ⠀⠘⠓⠤⠼⠿⠿⠿⠥⠽⠄⠘⠀⠀⠀⠀⠀⠘⠂⠼⠥⠽⠿⠿⠿⠤⠖⠛⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠀⠀⡞⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣤⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡤⠤⠶⠞⠋⠁⠀⠀⣸⠁⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀""";
        System.out.println("Up your rizz\n" + logo);
        greet();
        initDataFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    String answer = handleInput(scanner.nextLine());
                    System.out.println(SEPARATOR + answer + SEPARATOR);
                } catch (EmptyInputException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } else {
                scanner.close();
                break;
            }
        }
    }
}
