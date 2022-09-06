package utils;

public class Util {
    public static void showMessage(String message) {
        if (!message.isEmpty()) {
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
