import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Storage {
    private final String fileName;
    private File dataFile;

    Storage(String file) {
        fileName = file;
    }

    private boolean fileCreator() {
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
        fileCreator();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return (bufferedReader.lines());
    }

    public void saveData(List<Task> tasks) {
        fileCreator();
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            for (Task t: tasks) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
