package berry;

import berry.command.AddDeadlineCommand;
import berry.command.AddEventCommand;
import berry.command.AddTodoCommand;
import berry.command.DeleteCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.task.Deadline;
import berry.task.Event;
import berry.task.Task;
import berry.task.Todo;
import berry.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Berry {

    public static final String DIRECTORYPATH = "./data";
    public static final String FILEPATH = "./data/berry.txt";
    public static final String TEMPFILEPATH = "./data/temp.txt";
    public static final Scanner IN = new Scanner(System.in);
    public static final File DATAFILE = new File(FILEPATH);

    public static void main(String[] args) {
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();

        start(tasks, ui);
        while (true) {
            userInput = ui.getUserInput();
            executeCommand(userInput, tasks, ui);
        }
    }

    private static void start(ArrayList<Task> tasks, Ui ui) {
        try {
            checkDirectoryExists(ui);
            loadData(tasks);
            ui.printHelloMessage();
        } catch (FileNotFoundException | BerryException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private static void loadData(ArrayList<Task> tasks) throws FileNotFoundException {
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

    private static void checkDirectoryExists(Ui ui) {
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

    private static void checkFileExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void updateFile(int taskIndex, String userCommand, ArrayList<Task> tasks) throws IOException {
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

    public static void appendToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        Task newestTask = tasks.get(tasks.size() - 1);
        fw.write(newestTask.fileFormat() + System.lineSeparator());
        fw.close();
    }



    public static String extractCommand(String userInput) {
        String userCommand;
        if (userInput.contains("list")) {
            userCommand = "list";
        } else if (userInput.contains("todo")) {
            userCommand = "todo";
        } else if (userInput.contains("deadline")) {
            userCommand = "deadline";
        } else if (userInput.contains("event")) {
            userCommand = "event";
        } else if (userInput.contains("mark")) {
            userCommand = "mark";
        } else if (userInput.contains("delete")) {
            userCommand = "delete";
        } else if (userInput.contains("bye")) {
            userCommand = "bye";
        } else {
            throw new BerryException("Sorry, I'm not sure what you want me to do ._.");
        }
        return userCommand;
    }

    public static String[] extractDetails(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        String task = userInput.substring(dividerPosition + 1);
        return task.split("/");
    }

    public static void executeCommand(String userInput, ArrayList<Task> tasks, Ui ui) {
        try {
            String userCommand = extractCommand(userInput);
            switch (userCommand) {
            case "list":
                ListCommand list = new ListCommand(tasks, ui);
                list.execute();
                break;
            case "todo":
                AddTodoCommand addTodo = new AddTodoCommand(tasks, ui, userInput);
                addTodo.execute();
                break;
            case "deadline":
                AddDeadlineCommand addDeadline = new AddDeadlineCommand(tasks, ui, userInput);
                addDeadline.execute();
                break;
            case "event":
                AddEventCommand addEvent = new AddEventCommand(tasks, ui, userInput);
                addEvent.execute();
                break;
            case "mark":
                MarkCommand mark = new MarkCommand(tasks, ui, userCommand, userInput);
                mark.execute();
                break;
            case "delete":
                DeleteCommand delete = new DeleteCommand(tasks, ui, userCommand, userInput);
                delete.execute();
                break;
            case "bye":
                ui.printByeMessage();
                System.exit(0);
                // Fallthrough
            }
        } catch (BerryException | ArrayIndexOutOfBoundsException | IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}