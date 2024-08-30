package parser;

import java.util.Arrays;

/**
 * Contains a String instruction and String[] args where the first element in args is instruction.
 */
public class Instruction {
    private String instruction;
    private String[] args;

    Instruction(String instruction, String[] args) {
        this.instruction = instruction;
        this.args = args;
    }

    public String getInstruction() {
        return (instruction);
    }

    /**
     * Returns args variable without the first element.
     *
     * @return String array of arguments to instruction.
     */
    public String[] getArgs() {
        return (Arrays.copyOfRange(args, 1, args.length));
    }
}
