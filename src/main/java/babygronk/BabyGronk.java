package babygronk;

import java.util.Scanner;

import parser.EmptyInputException;
import parser.Instruction;
import parser.InvalidInputException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

/**
 * BabyGronk is the main class where all the program logic is handled.
 */
public class BabyGronk {
    /** Stores info and handles data of the file containing the task list **/
    private Storage storage;

    /** Prints formatted message **/
    private Ui ui;

    private TaskList taskList;

    /**
     * Constructor for babyGronk, takes a String as input.
     * String should be a relative file pathname to init and store the tasks to.
     *
     * @param fileName File pathname string.
     */
    public BabyGronk(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        taskList = new TaskList(storage.init());
    }

    private String handleInput(String input) {
        assert input != null;
        Parser parser = new Parser();
        Instruction instruction;
        try {
            instruction = parser.parseInstruction(input);
        } catch (InvalidInputException e) {
            return (e.getMessage());
        }
        if (instruction.getInstruction().equals("hi")) {
            return (Ui.sayHi());
        }
        if (instruction.getInstruction().equals("bye")) {
            storage.saveData(taskList.getTasks());
            ui.logOff();
        }
        if (instruction.getInstruction().equals("list")) {
            return (taskList.toString());
        }
        if (instruction.getInstruction().equals("mark") || instruction.getInstruction().equals("m")) {
            return (taskList.markTask(instruction.getArgs(), true));
        }
        if (instruction.getInstruction().equals("unmark") || instruction.getInstruction().equals("um")) {
            return (taskList.markTask(instruction.getArgs(), false));
        }
        if (instruction.getInstruction().equals("delete")) {
            return (taskList.delete(instruction.getArgs()));
        }
        if (instruction.getInstruction().equals("find")) {
            return (taskList.find(instruction.getArgs()[0]));
        }
        if (instruction.getInstruction().equals("todo") || instruction.getInstruction().equals("deadline")
                || instruction.getInstruction().equals("event")) {
            return (addTask(instruction));
        }
        return (null);
    }

    private String addTask(Instruction instruction) {
        String task = instruction.getInstruction();
        String[] args = instruction.getArgs();
        switch (task) {
        case "todo":
            return (taskList.add(new ToDo(args[0])));
        case "deadline":
            return (taskList.add(new Deadline(args[0], args[1])));
        case "event":
            return (taskList.add(new Event(args[0], args[1], args[2])));
        default:
            return ("Invalid task: " + task + " (parser failed probably)");
        }
    }

    /**
     * Starts by displaying welcome message.
     * Runs the program on a loop waiting for user input using scanner and handling it.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    String input = Parser.parseInput(scanner.nextLine());
                    ui.printMessage(handleInput(input));
                } catch (EmptyInputException e) {
                    ui.printMessage(e.getMessage());
                }
            } else {
                scanner.close();
                break;
            }
        }
    }

    public String getResponse(String input) {
        try {
            return handleInput(Parser.parseInput(input));
        } catch (EmptyInputException e) {
            return (e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BabyGronk("./data/BabyGronk.txt").run();
    }
}
