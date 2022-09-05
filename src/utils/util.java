package utils;

public class util {
    public static void showMessage(String message) {
        if (!message.equals("")) {
            for (int i = 0; i < message.length() + 4; i++) {
                System.out.print("-");
            }

            System.out.println("\n| " + message + " |");

            for (int i = 0; i < message.length() + 4; i++) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}
