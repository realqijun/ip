package seedu.babygronk;

import babygronk.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class StorageTest {

    @Test
    public void testInvalidFiles() {
        assertNull(new Storage("/////").init());
        assertNull(new Storage("/Users/new").init());
    }
}
