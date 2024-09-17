package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import babygronk.Storage;

class StorageTest {

    void cleanUp(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Files.delete(file.toPath());
            Files.delete(file.getParentFile().toPath());
        }
    }

    @Test
    void testFileCreated() throws IOException {
        String testFileName = "test_data/test.txt";
        Storage storage = new Storage(testFileName);
        storage.init();
        File testFile = new File(testFileName);
        assertTrue(testFile.exists(), "The file should be created.");
        cleanUp(testFileName);
    }

    @Test
    void testReadFromFile() throws IOException {
        String testFileName = "tmp1/test.t";
        Files.createDirectories(new File(testFileName).getParentFile().toPath());
        Files.write(new File(testFileName).toPath(), "Task 1\nTask 2".getBytes());

        Storage storage = new Storage(testFileName);
        Stream<String> lines = storage.init();
        assertNotNull(lines, "Stream of lines should not be null.");
        cleanUp(testFileName);
    }

    @Test
    void testInvalidPath() {
        Storage faultyStorage = new Storage(".///test.txt/");
        Stream<String> result = faultyStorage.init();
        assertNull(result, "Init should return null when file cannot be created.");
    }
}
