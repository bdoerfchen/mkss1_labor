package hsb.mkss1.ordersystem.adapters.ui.util;

import java.io.IOError;


public class Input {

    private Input() {
    }

    public static boolean readBoolean() {
        boolean result;
        try {
            result = Boolean.parseBoolean(readString());
        } catch (NumberFormatException _) {
            result = false;
        }

        return result;
    }

    public static double readDouble() {
        double result;
        try {
            result = Double.parseDouble(readString());
        } catch (NumberFormatException _) {
            result = 0d;
        }

        return result;
    }

    public static float readFloat() {
        float result;
        try {
            result = Float.parseFloat(readString());
        } catch (NumberFormatException _) {
            result = 0f;
        }

        return result;
    }

    public static int readInt() {
        int result;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException _) {
            result = 0;
        }

        return result;
    }

    public static String readString() {
        String result;

        try {
            result = IO.readln();
        } catch (IOError _) {
            result = "";
        }
        return result;
    }

    // Only for test purposes
    static void main() {
        int input = 0;
        String outputText = "Input was: ";
        while (input != -1) {
            IO.print("Enter text: ");
            IO.println(outputText + Input.readString());
            IO.print("Enter float: ");
            IO.println(outputText + Input.readFloat());
            IO.print("Enter double: ");
            IO.println(outputText + Input.readDouble());
            IO.print("Enter boolean: ");
            IO.println(outputText + Input.readBoolean());
            IO.print("Enter integer number (Cancel with -1): ");
            input = Input.readInt();
            IO.println(outputText + input);
        }
    }
}
