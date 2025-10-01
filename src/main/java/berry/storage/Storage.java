package berry.storage;

import berry.data.BerryException;
import berry.task.Deadline;
import berry.task.Event;
import berry.task.Task;
import berry.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages reading from and writing to the berry.txt.
 */
public class Storage {

    private static final String DIRECTORYPATH = "./data";
    private static final String FILEPATH = "./data/berry.txt";
    private static final String TEMPFILEPATH = "./data/temp.txt";
    private final File dataFile;

    public Storage() {
        dataFile = new File(FILEPATH);
    }

    /**
     * Loads all tasks from berry.txt
     * <p>
     * This method first ensures that the storage directory and file exist
     * by calling {@code checkDirectoryExists()}. It then reads each line from
     * berry.txt, and instantiates the appropriate {@link Task} subclass.
     * Each task is added to an ArrayList, which is returned to the caller.
     *
     * @return an ArrayList containing all tasks loaded from storage.
     * @throws IOException If an error occurs while accessing or reading the file.
     */
    public ArrayList<Task> loadData() throws IOException {
        checkDirectoryExists();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(dataFile);
        String currentLine;
        String[] taskDetails;
        while (scan.hasNext()) {
            currentLine = scan.nextLine();
            taskDetails = currentLine.split("\\|");
            switch (taskDetails.length) {
            case 3:
                tasks.add(new Todo(taskDetails[2].trim()));
                break;
            case 4:
                tasks.add(new Deadline(taskDetails[2].trim(), taskDetails[3].trim()));
                break;
            case 5:
                tasks.add(new Event(taskDetails[2].trim(), taskDetails[3].trim(), taskDetails[4].trim()));
                break;
            default:
                throw new BerryException("wrong data loaded.");
            }
            tasks.get(tasks.size() - 1).setDone(taskDetails[1].trim().equals("X"));
        }
        scan.close();
        return tasks;
    }

    /**
     * Ensures that the storage directory and data file exist.
     * <p>
     * If the directory does not exist, it is created. Then checks whether berry.txt exists
     * and creates the file if needed.
     *
     * @throws IOException If an error occurs while creating the directory or file.
     */
    public void checkDirectoryExists() throws IOException {
        File directory = new File(DIRECTORYPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        checkFileExists(dataFile);
    }

    /**
     * Ensures that the specified file exists.
     * <p>
     * If the file does not exist, this method attempts to create a new empty file.
     *
     * @param file The {@link File} object to check or create.
     * @throws IOException If an error occurs while creating the file.
     */
    public void checkFileExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Updates berry.txt to reflect a change to a task.
     * <p>
     * This method rewrites berry.txt using a temporary file. Each task
     * line from the original file is copied over, except for the task at
     * the specified index. That line is conditionally replaced with the
     * updated task from the given list, depending on the command. The
     * temporary file is then renamed to replace the original storage file.
     *
     * @param taskIndex   The index of the task in the list to update.
     * @param userCommand The command type that triggered the update.
     * @param tasks       The current list of tasks
     * @throws IOException If an error occurs while writing or replacing the file.
     */
    public void updateFile(int taskIndex, String userCommand, ArrayList<Task> tasks) throws IOException {
        File tempFile = new File(TEMPFILEPATH);
        checkFileExists(tempFile);
        FileWriter tempFileWriter = new FileWriter(tempFile);
        Scanner scan = new Scanner(dataFile);
        String currentLine;
        int loopIndex = 0;
        while (scan.hasNext()) {
            currentLine = scan.nextLine();
            if (loopIndex == taskIndex) {
                if (userCommand.equals("mark")) {
                    tempFileWriter.write(tasks.get(loopIndex).fileFormat() + System.lineSeparator());
                }
                loopIndex++;
                continue;
            }
            tempFileWriter.write(currentLine + System.lineSeparator());
            loopIndex++;
        }
        scan.close();
        tempFileWriter.close();
        dataFile.delete();
        tempFile.renameTo(dataFile);
    }

    /**
     * Appends the most recently added task to the storage file.
     * <p>
     * This method retrieves the last task in the provided list and writes its
     * serialised format to the end of berry.txt.
     *
     * @param tasks The list of tasks.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void appendToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        Task newestTask = tasks.get(tasks.size() - 1);
        fw.write(newestTask.fileFormat() + System.lineSeparator());
        fw.close();
    }
}
