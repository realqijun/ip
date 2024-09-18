package seedu.babygronk;

//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import babygronk.Storage;

class StorageTest {

    //private static final String TEST_DATA_FILE = "src/test/data/StorageTest/test.txt";
    //@AfterEach
    //void cleanUp() throws IOException {
    //    File file = new File(TEST_DATA_FILE);
    //    if (file.exists()) {
    //        try {
    //            Thread.sleep(100);
    //        } catch (InterruptedException e) {
    //            Thread.currentThread().interrupt();
    //        }
    //        Files.deleteIfExists(file.toPath());
    //        Files.deleteIfExists(file.getParentFile().toPath());
    //    }
    //}
    //
    //@Test
    //void testFileCreated() throws IOException {
    //    Storage storage = new Storage(TEST_DATA_FILE);
    //    storage.init();
    //    File testFile = new File(TEST_DATA_FILE);
    //    assertTrue(testFile.exists(), "The file should be created.");
    //}
    //
    //@Test
    //void testReadFromFile() throws IOException {
    //    Files.createDirectories(new File(TEST_DATA_FILE).getParentFile().toPath());
    //    Files.write(new File(TEST_DATA_FILE).toPath(), "Task 1\nTask 2".getBytes());
    //
    //    Storage storage = new Storage(TEST_DATA_FILE);
    //    Stream<String> lines = storage.init();
    //    assertNotNull(lines, "Stream of lines should not be null.");
    //}

    @Test
    void testInvalidPath() throws IOException {
        Storage faultyStorage = new Storage(".///test.txt/");
        Stream<String> result = faultyStorage.init();
        assertNull(result, "Init should return null when file cannot be created.");

        File file = new File(".///test.txt");
        if (file.exists()) {
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(file.getParentFile().toPath());
        }
    }
}
