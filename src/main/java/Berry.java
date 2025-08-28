import java.util.Scanner;

public class Berry {
    public static void main(String[] args) {
        String chatbotName = "Berry";
        String userCommand;
        Task[] tasks = new Task[100];   //array of Task objects
        int numberOfTasks = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nHello! I'm " + chatbotName
                + "\nWhat can I do for you?\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        userCommand = in.nextLine();  //get command from user
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                printList(tasks, numberOfTasks);
            } else if (userCommand.contains("mark")) {
                toggleTaskStatus(userCommand, tasks);
            } else {
                addTask(userCommand, tasks, numberOfTasks);
                numberOfTasks++;
            }
            userCommand = in.nextLine();
        }
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nBye. Hope to see you again soon!"
                + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
    }

    public static void addTask(String command, Task[] tasks, int numberOftasks) {
        tasks[numberOftasks] = new Task(command);
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nadded: " + command + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
    }

    public static void printList(Task[] tasks, int numberOftasks) {
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
        for (int i = 0; i < numberOftasks; i++) {
            System.out.println(i + 1 + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
    }

    public static void toggleTaskStatus(String command, Task[] tasks) {
        int dividerPosition = command.indexOf(" ");

        //task number to identify which task to mark/unmark
        int taskNumber = Integer.parseInt(command.substring(dividerPosition).trim()) - 1;
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
        if (command.contains("un")) {
            tasks[taskNumber].unmarkAsDone();
            System.out.println("Okay, I've marked this task as not done yet:\n[ ] "
                    + tasks[taskNumber].getDescription() + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        } else {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] "
                    + tasks[taskNumber].getDescription() + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        }
    }
}
