package babygronk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

import task.Task;

/**
 * Creates file and reads and stores data in it.
 */
public class Storage {
    private static final String DEFAULT_STORAGE_NAME = "./data/BabyGronk.txt";
    private final String fileName;

    /**
     * Constructor for Storage class. Assigns default storage file if input is null or empty.
     *
     * @param file File to be used as Storage.
     */
    public Storage(String file) {
        if (file == null || file.isEmpty()) {
            fileName = DEFAULT_STORAGE_NAME;
        } else {
            fileName = file;
        }
    }

    private boolean fileCreated() {
        File dataFile = new File(fileName);
        File parentDir = dataFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean directoryCreated = parentDir.mkdirs();
            if (!directoryCreated) {
                System.out.println("Failed to create directory: " + parentDir);
                return (false);
            }
        }

        if (!dataFile.exists()) {
            boolean isCreated;
            try {
                isCreated = dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file");
                return (false);
            }
            if (!isCreated) {
                System.out.println("File was not created");
                return (false);
            }
        }
        return (true);
    }

    /**
     * Creates the file if it doesn't exist.
     * Reads from the file if it exists.
     *
     * @return Stream of Strings for each line in the file or null if empty
     */
    public Stream<String> init() {
        if (fileCreated()) {
            FileReader fileReader;
            try {
                fileReader = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return (null);
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return (bufferedReader.lines());
        }
        return (null);
    }

    /**
     * Saves tasks into the storage file using bufferedWriter.
     *
     * @param tasks List of tasks.
     */
    public void saveData(Stream<Task> tasks) {
        if (fileCreated()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                tasks.forEach(task -> {
                    try {
                        bufferedWriter.write(task.toStorageFormat());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        System.out.println("Failed to save task: " + task);
                    }
                });
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }

        System.out.println("Data was not saved to file");
    }
}
