import java.util.Scanner;

public class Berry {
    public static void main(String[] args) {
        String chatbotName = "Berry";
        System.out.println("‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nHello! I'm " + chatbotName + "\nWhat can I do for you?\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
        echo();
        System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\nBye. Hope to see you again soon!\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧");
    }

    public static void echo(){
        String inputText;
        Scanner in = new Scanner(System.in);
        inputText = in.nextLine();
        while (!inputText.equals("bye")) {
            System.out.println("\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n" + inputText + "\n‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧‧⋆ ✧˚₊‧⋆. ✧˚₊‧⋆‧\n");
            inputText = in.nextLine();
        }
        return;
    }
}
