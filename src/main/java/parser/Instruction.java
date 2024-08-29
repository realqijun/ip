package parser;

import java.util.Arrays;

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

    public String[] getArgs() {
        return (Arrays.copyOfRange(args, 1, args.length));
    }
}
