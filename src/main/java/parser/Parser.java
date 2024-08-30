package parser;

import java.util.Arrays;

public class Parser {

    public Parser() {
    }

    public static String   parseInput(String input) throws EmptyInputException {
        if (input == null || input.isEmpty()) {
            throw new EmptyInputException ("What bro? You're too fanum taxed\n");
        }
        return (String.join(" ", input.strip().split(" ")));
    }

    public Instruction parseInstruction(String input) throws InvalidInputException {
        String[] args = input.split(" ");
        if (input.startsWith("bye")) {
            return (new Instruction(args[0], args));
        }
        if (input.startsWith("list")) {
            return (new Instruction(args[0], args));
        }
        if (args[0].equals("mark") || args[0].equals("unmark") || args[0].equals("m") || args[0].equals("um")) {
            if (args.length < 2) {
                throw new InvalidInputException("Are you acoustic? Give me a number! (mark/unmark [index])\n");
            }
            return (new Instruction(args[0], args));
        }
        if (args[0].equals("delete")) {
            if (args.length < 2) {
                throw new InvalidInputException("How can you not delete properly? (delete [index])\n");
            }
            return (new Instruction(args[0], args));
        }
        if (args[0].equals("find")) {
            String[] findArgs = input.split(" ", 2);
            return (new Instruction(args[0], findArgs));
        }
        if (args[0].equals("todo") || args[0].equals("deadline") || args[0].equals("event")) {
            if (args.length < 2) {
                throw new InvalidInputException("No grimace shake for you ([task_type] [task_name] [args])\n");
            }
            switch (args[0]) {
            case "todo":
                String[] t_temp = input.split(" ", 2);
                return (new Instruction(t_temp[0], t_temp));
            case "deadline":
                String[] d_temp = input.split(" ", 2);
                String[] deadlineArgs = d_temp[1].split(" /by ");
                if (deadlineArgs.length != 2) {
                    throw new InvalidInputException("no deadline!\n");
                }
                String[] d_ret = Arrays.copyOfRange(deadlineArgs, 2, deadlineArgs.length);
                d_ret[0] = d_temp[0];
                System.arraycopy(deadlineArgs, 0, d_ret, 1, deadlineArgs.length);
                return (new Instruction(d_temp[0], d_ret));
            case "event":
                String[] e_temp = input.split(" ", 2);
                String[] eventFromArgs = e_temp[1].split(" /from | /to ");
                if (eventFromArgs.length != 3) {
                    throw new InvalidInputException("event start/end time is not determined\n");
                }
                String[] e_ret = Arrays.copyOf(eventFromArgs, eventFromArgs.length + 1);
                e_ret[0] = e_temp[0];
                System.arraycopy(eventFromArgs, 0, e_ret, 1, eventFromArgs.length);
                return (new Instruction(e_temp[0], e_ret));
            }
        }
        throw new InvalidInputException("invalid task type (only todo, deadline, event allowed)\n");
    }
}
