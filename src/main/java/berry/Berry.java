package berry;

import berry.command.Command;
import berry.data.BerryException;
import berry.data.TaskList;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.ui.Ui;

import java.io.IOException;

public class Berry {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Berry() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void run() {
        ui.printHelloMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command command = Parser.extractCommand(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (IOException | BerryException | ArrayIndexOutOfBoundsException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Berry().run();
    }
}