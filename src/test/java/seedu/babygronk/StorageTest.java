package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import babygronk.Storage;

public class StorageTest {
    private static final String TEST_FILE = "./data/testFile.txt";

    @Test
    public void testFileCreated() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        Storage storage = new Storage(TEST_FILE);
        assertFalse(file.exists(), "Test file should not exist initially");
        storage.init();
        assertTrue(file.exists(), "Test file should be created after calling init()");
        file.delete();
    }

    @Test
    public void testInitReadsFile() throws IOException {
        List<String> dummyData = List.of("Task 1", "Task 2", "Task 3");
        Path path = Paths.get(TEST_FILE);
        Files.write(path, dummyData);
        Stream<String> lines = new Storage(TEST_FILE).init();
        assertNotNull(lines, "Stream of lines should not be null");
        List<String> readLines = lines.toList();
        assertEquals(dummyData, readLines, "Read lines should match the content of the file");
        Files.delete(path);
    }

    @Test
    public void testSaveDataFailsGracefully() {
        new Storage(TEST_FILE).saveData(Stream.empty());
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "Test file should exist");
        assertEquals(0, file.length(), "File should be empty when no tasks are saved");
        file.delete();
    }
}
