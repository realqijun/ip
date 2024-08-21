import javax.swing.*;
import java.util.Scanner;

public class BabyGronk {
    private final static String seperators =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";
    private static String[] history = new String[100];
    private static int h_index = 0;

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

    private static String getPrompt() {
        return new Scanner(System.in).nextLine();
    }

    private static String handleInput(String input) {
        if (input == null || input.isEmpty()) {
            return ("What bro? You're not skibidi enough\n");
        }
        if (input.equals("bye")) {
            logOff();
        }
        if (input.equals("list")) {
            return (getHistory());
        }
        addHistory(input);
        return ("added: " + input + "\n");
    }

    private static void    addHistory(String input) {
        history[h_index++] = input;
    }

    private static String    getHistory() {
        StringBuilder hist = new StringBuilder();
        for (int i = 0; i < h_index; i++) {
            hist.append(i + 1).append(".: ").append(history[i]).append("\n");
        }
        return hist.toString();
    }

    public static void main(String[] args) {
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
