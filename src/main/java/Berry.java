import java.util.Scanner;

public class Berry {
    public static void main(String[] args) {
        String chatbotName = "Berry";
        String userCommand;
        Scanner in = new Scanner(System.in);
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nHello! I'm " + chatbotName + "\nWhat can I do for you?\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        userCommand = in.nextLine();  //get command from user
        while (!userCommand.equals("bye")) {
            echo(userCommand);
            userCommand = in.nextLine();
        }
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nBye. Hope to see you again soon!\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
    }

    public static void echo(String command) {
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n" + command + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        return;
    }
}
