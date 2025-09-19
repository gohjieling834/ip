package berry;

import berry.task.Deadline;
import berry.task.Event;
import berry.task.Task;
import berry.task.Todo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Berry {
    public static final String DIVIDER = "‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧";
    public static final String CHATBOT_NAME = "Berry";
    public static final String DIRECTORYPATH = "./data";
    public static final String FILEPATH = "./data/berry.txt";
    public static final String TEMPFILEPATH = "./data/temp.txt";
    public static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();
        checkDirectoryExists();
        try {
            loadData(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        }
        printHelloMessage();
        while (true) {
            userInput = getUserInput(IN);
            executeCommand(userInput, tasks);
        }
    }

    private static void loadData(ArrayList<Task> tasks) throws FileNotFoundException {
        File dataFile = new File(FILEPATH);
        Scanner scan = new Scanner(dataFile);
        String currentLine;
        String[] taskDetails;
        while (scan.hasNext()){
            currentLine = scan.nextLine();
            taskDetails = currentLine.split("\\|");
            if(taskDetails.length == 3){
                tasks.add(new Todo (taskDetails[2].trim()));
            } else if (taskDetails.length == 4) {
                tasks.add(new Deadline(taskDetails[2].trim(), taskDetails[3].trim()));
            } else {
                tasks.add(new Event(taskDetails[2].trim(), taskDetails[3].trim(), taskDetails[4].trim()));
            }
            tasks.get(tasks.size()-1).setDone(taskDetails[1].trim().equals("X"));
        }
        scan.close();
    }

    private static void checkDirectoryExists() {
        File directory = new File(DIRECTORYPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            checkFileExists(FILEPATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkFileExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void updateFile(int taskIndex, String userCommand, ArrayList<Task> tasks) throws IOException {
        checkFileExists(TEMPFILEPATH);
        File file = new File(FILEPATH);
        File tempFile = new File(TEMPFILEPATH);
        FileWriter tempFileWriter = new FileWriter(tempFile);
        Scanner scan = new Scanner(file);
        String currentLine;
        int loopIndex = 0;
        while (scan.hasNext()) {
            currentLine = scan.nextLine();
            if(loopIndex == taskIndex){
                if(userCommand.equals("mark")){
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
        file.delete();
        tempFile.renameTo(file);
    }

    private static void appendToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        Task newestTask = tasks.get(tasks.size()-1);
        fw.write(newestTask.fileFormat() + System.lineSeparator());
        fw.close();
    }

    public static void printHelloMessage() {
        System.out.println(DIVIDER + "\n" + "Hello! I'm " + CHATBOT_NAME
                + "\nWhat can I do for you?" + "\n" + DIVIDER + "\n");
    }

    public static void printByeMessage() {
        System.out.println("\n" + DIVIDER + "\nBye. Hope to see you again soon!"
                + "\n" + DIVIDER);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("\n" + DIVIDER + "\n" + errorMessage + "\n" + DIVIDER + "\n");
    }

    public static void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\n" + DIVIDER + "\nGot it. I've added this task:\n  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + DIVIDER + "\n");
    }

    public static String getUserInput(Scanner in) {
        return in.nextLine();
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

    public static void executeCommand(String userInput, ArrayList<Task> tasks) {
        try {
            String userCommand = extractCommand(userInput);
            switch (userCommand) {
            case "list":
                printList(tasks);
                break;
            case "todo":
                addTodo(userInput, tasks);
                break;
            case "deadline":
                addDeadline(userInput, tasks);
                break;
            case "event":
                addEvent(userInput, tasks);
                break;
            case "mark":
                toggleTaskStatus(userInput, userCommand, tasks);
                break;
            case "delete":
                deleteTask(userInput, userCommand, tasks);
                break;
            case "bye":
                printByeMessage();
                System.exit(0);
                // Fallthrough
            }
        } catch (BerryException e) {
            System.out.println("\n" + DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER + "\n");
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void addTodo(String userInput, ArrayList<Task> tasks) throws IOException{
        if (userInput.trim().length() < 5) {
            throw new BerryException("Your description of todo cannot be empty!");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description));
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }

    public static void addDeadline(String userInput, ArrayList<Task> tasks) throws IOException {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Please enter both task description and by when. Thank you :)");
        }
        int startIndexOfBy = taskDetails[1].indexOf("by") + 2;  // + 2 because the substring start index should begin after by
        String description = taskDetails[0].trim();
        String by = taskDetails[1].substring(startIndexOfBy).trim();
        tasks.add(new Deadline(description, by));
        printAddTaskMessage(tasks);
        appendToFile(tasks);
    }

    public static void addEvent(String userInput, ArrayList<Task> tasks) throws IOException {
        String[] taskDetails = extractDetails(userInput);
        if (taskDetails.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Please enter all the event detail (description, from, to). Thank you :)");
        }
        int startIndexOfFrom = taskDetails[1].indexOf("from") + 4;  // + 4 because the substring start index should begin after from
        int startIndexOfTo = taskDetails[2].indexOf("to") + 2;  // + 2 because the substring start index should begin after to
        String description = taskDetails[0].trim();
        String from = taskDetails[1].substring(startIndexOfFrom).trim();
        String to = taskDetails[2].substring(startIndexOfTo).trim();
        tasks.add(new Event(description, from, to));
        appendToFile(tasks);
        printAddTaskMessage(tasks);
    }

    public static void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new BerryException("There's no tasks in the list. Would you like to start adding tasks?");
        }
        System.out.println("\n" + DIVIDER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(DIVIDER + "\n");
    }

    public static void toggleTaskStatus(String userInput, String userCommand, ArrayList<Task> tasks) {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            if (userInput.contains("un")) {
                tasks.get(taskNumber).markAsUndone();
                System.out.println("\n" + DIVIDER + "\n" + "Okay, I've marked this task as not done yet:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            } else {
                tasks.get(taskNumber).markAsDone();
                System.out.println("\n" + DIVIDER + "\n" + "Nice! I've marked this task as done:\n  "
                        + tasks.get(taskNumber) + "\n" + DIVIDER + "\n");
            }
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            printErrorMessage("Sorry, I don't know which task to mark/unmark. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String userInput, String userCommand, ArrayList<Task> tasks) {
        int dividerPosition = userInput.indexOf(" ");

        try {
            int taskNumber = Integer.parseInt(userInput.substring(dividerPosition).trim()) - 1;
            System.out.println("\n" + DIVIDER + "\n" + "Okay, I've removed this task:\n  "
                    + tasks.remove(taskNumber) + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n"
                    + DIVIDER + "\n");
            updateFile(taskNumber, userCommand, tasks);
        } catch (NumberFormatException e) {
            printErrorMessage("Sorry, I don't know which task to delete. Please enter the task number, thank you! :)");
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("This task number does not exist! :|");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}