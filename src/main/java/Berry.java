import java.util.Scanner;

public class Berry {
    public static void main(String[] args) {
        String chatbotName = "Berry";
        String userCommand;
        String[] tasks = new String[100];
        int numberOftasks = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nHello! I'm " + chatbotName + "\nWhat can I do for you?\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        userCommand = in.nextLine();  //get command from user
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                list(tasks, numberOftasks);
            } else {
                addTask(userCommand, tasks, numberOftasks);
                numberOftasks++;
            }
            userCommand = in.nextLine();
        }
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nBye. Hope to see you again soon!\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
    }

    public static void addTask(String command, String[] tasks, int numberOftasks) {
        tasks[numberOftasks] = command;
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nadded: " + command + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
    }

    public static void list(String[] tasks, int numberOftasks) {
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
        for (int i = 0; i < numberOftasks; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
    }
}
