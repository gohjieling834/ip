package berry.storage;

import berry.BerryException;
import berry.task.Deadline;
import berry.task.Event;
import berry.task.Task;
import berry.task.Todo;
import berry.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String DIRECTORYPATH = "./data";
    private static final String FILEPATH = "./data/berry.txt";
    private static final String TEMPFILEPATH = "./data/temp.txt";
    private static final File DATAFILE = new File(FILEPATH);

    public void loadData(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner scan = new Scanner(DATAFILE);
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
    }

    public void checkDirectoryExists(Ui ui) {
        File directory = new File(DIRECTORYPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            checkFileExists(DATAFILE);
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void checkFileExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void updateFile(int taskIndex, String userCommand, ArrayList<Task> tasks) throws IOException {
        File tempFile = new File(TEMPFILEPATH);
        checkFileExists(tempFile);
        FileWriter tempFileWriter = new FileWriter(tempFile);
        Scanner scan = new Scanner(DATAFILE);
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
        DATAFILE.delete();
        tempFile.renameTo(DATAFILE);
    }

    public void appendToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        Task newestTask = tasks.get(tasks.size() - 1);
        fw.write(newestTask.fileFormat() + System.lineSeparator());
        fw.close();
    }
}
