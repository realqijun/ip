import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BabyGronk {
    private final static    String seperators =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";
    private static  List<Task> tasks = new ArrayList<>();

    private static void logOff() {
        String goodByeMessage = seperators +
                """
                Smell ya later.
                """ +
                seperators;
        System.out.println(goodByeMessage);
        System.exit(0);
    }

    private static void greet() {
        String welcomeMessage = seperators +
                """ 
                What's up ohio? I'm BabyGronk, let's see how sigma you are
                How much aura do you have?
                """ +
                seperators;
        System.out.println(welcomeMessage);
    }

    private static String   getPrompt() {
        return new Scanner(System.in).nextLine();
    }

    private static String   handleInput(String input) {
        if (input == null || input.isEmpty()) {
            return ("What bro? You're not skibidi enough\n");
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
                return ("invalid mark/unmark command\n");
            }
            if (input.startsWith("unmark")) {
                return (markTask(args[1], false));
            } else {
                return (markTask(args[1], true));
            }
        }
        addTask(input);
        return ("added: " + input + "\n");
    }

    private static void addTask(String input) {
        tasks.add(new Task(input));
    }

    private static String   listTasks() {
        StringBuilder hist = new StringBuilder("To do list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            hist.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return hist.toString();
    }

    private static String   markTask(String input, boolean status) {
        if (input == null || input.isEmpty()) {
            return ("invalid task\n");
        }
        int toMark = Integer.parseInt(input);
        if (toMark > tasks.size() || toMark < 1) {
            return ("invalid task\n");
        }
        Task task = tasks.get(toMark - 1);
        task.setDone(status);
        if (status) {
            return ("Let's go! +100 aura points!\n" + task + "\n");
        } else {
            return ("Bruh, you're cooked, -500 aura points\n" + task + "\n");
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
        while (true) {
            String answer = handleInput(getPrompt());
            System.out.println(seperators + answer + seperators);
        }
    }
}
