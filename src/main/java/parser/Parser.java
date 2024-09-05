package parser;

import java.util.Arrays;

/**
 * Parses input from user given by run method in BabyGronk class.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Formats the input by splitting it and then joining it with single space.
     *
     * @param input String from input.
     * @return Single string with single space between words.
     * @throws EmptyInputException If the string is null or empty.
     */
    public static String parseInput(String input) throws EmptyInputException {
        if (input == null || input.isEmpty()) {
            throw new EmptyInputException("What bro? You're too fanum taxed\n");
        }
        return (String.join(" ", input.strip().split("\\s+")));
    }

    /**
     * Parses input into formatted instructions.
     *
     * @param input Input given from user.
     * @return Instruction object with parsed input.
     * @throws InvalidInputException When input is not valid/defined in project page.
     */
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
                String[] todoTemp = input.split(" ", 2);
                return (new Instruction(todoTemp[0], todoTemp));
            case "deadline":
                String[] deadlineTemp = input.split(" ", 2);
                String[] deadlineArgs = deadlineTemp[1].split(" /by ");
                if (deadlineArgs.length != 2) {
                    throw new InvalidInputException("no deadline!\n");
                }
                String[] deadlineRet = Arrays.copyOfRange(deadlineArgs, 2, deadlineArgs.length);
                deadlineRet[0] = deadlineTemp[0];
                System.arraycopy(deadlineArgs, 0, deadlineRet, 1, deadlineArgs.length);
                return (new Instruction(deadlineTemp[0], deadlineRet));
            case "event":
                String[] eventTemp = input.split(" ", 2);
                String[] eventArgs = eventTemp[1].split(" /from | /to ");
                if (eventArgs.length != 3) {
                    throw new InvalidInputException("event start/end time is not determined\n");
                }
                String[] eventReturn = Arrays.copyOf(eventArgs, eventArgs.length + 1);
                eventReturn[0] = eventTemp[0];
                System.arraycopy(eventArgs, 0, eventReturn, 1, eventArgs.length);
                return (new Instruction(eventTemp[0], eventReturn));
            default:
            }
        }
        throw new InvalidInputException("invalid task type (only todo, deadline, event allowed)\n");
    }
}
