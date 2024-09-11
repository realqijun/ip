package parser;

public enum InvalidCommands {
    INVALID_MARK_CMD("Are you acoustic? Give me a number! (mark/unmark [index])\n"),
    INVALID_DELETE_CMD("How can you not delete properly? (delete [index])\n"),
    INVALID_TASK_CMD("No grimace shake for you ([task_type] [task_name] [args])\n"),
    INVALID_INPUT("Sorry, I don't know that command\n"),
    INVALID_DEADLINE("What is the deadline?\n"),
    INVALID_EVENT("The event start/end time is not determined\n");

    private final String ERROR_MSG;

    InvalidCommands(String s) {
        ERROR_MSG = s;
    }

    public String getErrorMessage() {
        return ERROR_MSG;
    }
}
