package seedu.babygronk;

import org.junit.jupiter.api.Test;
import task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoTest() {
        assertEquals(new ToDo("gang").toString(), new ToDo("gang").toString());
    }

    @Test
    public void displayTest() {
        assertEquals(new ToDo("test").display(), new ToDo("test").display());
    }
}
