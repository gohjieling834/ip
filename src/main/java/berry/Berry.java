package berry;

import berry.command.AddDeadlineCommand;
import berry.command.AddEventCommand;
import berry.command.AddTodoCommand;
import berry.command.DeleteCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.Task;
import berry.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public class Berry {

    public static void main(String[] args) {
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage();

        start(tasks, ui, storage);
        while (true) {
            userInput = ui.getUserInput();
            executeCommand(userInput, tasks, ui, storage);
        }
    }

    private static void start(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            storage.checkDirectoryExists(ui);
            storage.loadData(tasks);
            ui.printHelloMessage();
        } catch (FileNotFoundException | BerryException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }


    public static void executeCommand(String userInput, ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            String userCommand = Parser.extractCommand(userInput);
            switch (userCommand) {
            case "list":
                ListCommand list = new ListCommand(tasks, ui);
                list.execute();
                break;
            case "todo":
                AddTodoCommand addTodo = new AddTodoCommand(tasks, ui, storage, userInput);
                addTodo.execute();
                break;
            case "deadline":
                AddDeadlineCommand addDeadline = new AddDeadlineCommand(tasks, ui, storage, userInput);
                addDeadline.execute();
                break;
            case "event":
                AddEventCommand addEvent = new AddEventCommand(tasks, ui, storage, userInput);
                addEvent.execute();
                break;
            case "mark":
                MarkCommand mark = new MarkCommand(tasks, ui, storage, userCommand, userInput);
                mark.execute();
                break;
            case "delete":
                DeleteCommand delete = new DeleteCommand(tasks, ui, storage, userCommand, userInput);
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