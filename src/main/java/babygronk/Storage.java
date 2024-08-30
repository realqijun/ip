package babygronk;

import task.Task;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Storage {
    private final String fileName;

    public Storage(String file) {
        fileName = file;
    }

    private boolean createFile() {
        File dataFile = new File(fileName);
        if (!dataFile.exists()) {
            boolean directoryCreated = dataFile.getParentFile().mkdirs();
            if (!directoryCreated) {
                System.out.println("Directory was not created");
                return (false);
            }
            boolean fileCreated = false;
            try {
                fileCreated = dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
            if (!fileCreated) {
                System.out.println("File was not created");
                return (false);
            }
        }
        return (true);
    }

    public Stream<String> load() {
        if (createFile()) {
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

    public void saveData(List<Task> tasks) {
        if (createFile()) {
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                for (Task t : tasks) {
                    bufferedWriter.write(t.toString());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
    }
}
