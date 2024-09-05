package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import babygronk.Storage;

public class StorageTest {

    @Test
    public void testInvalidFiles() {
        assertNull(new Storage("/////").init());
        assertNull(new Storage("/Users/new").init());
    }
}
