package seedu.babygronk;

import org.junit.jupiter.api.Test;
import task.TaskList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void taskListTest() {
        List<String> taskList = new ArrayList<>();
        taskList.add("[T][ ] go home 1234");
        assertEquals(new TaskList(taskList.stream()).toString(), new TaskList(taskList.stream()).toString());
    }
}
