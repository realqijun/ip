package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.ToDo;

public class TodoTest {

    @Test
    public void todoTest() {
        assertEquals(new ToDo("gang").toString(), new ToDo("gang").toString());
    }

    @Test
    public void displayTest() {
        assertEquals(new ToDo("test").toStorageFormat(), new ToDo("test").toStorageFormat());
    }
}
