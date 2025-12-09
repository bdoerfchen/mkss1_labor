package hsb.mkss1.order_system.adapters.cli.util;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;


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
            result = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException _) {
            result = "";
        }
        return result;
    }

    // Only for test purposes
    static void main() {
        int input = 0;
        String outputText = "Input was: ";
        while (input != -1) {
            System.out.print("Enter text: ");
            System.out.println(outputText + Input.readString());
            System.out.print("Enter float: ");
            System.out.println(outputText + Input.readFloat());
            System.out.print("Enter double: ");
            System.out.println(outputText + Input.readDouble());
            System.out.print("Enter boolean: ");
            System.out.println(outputText + Input.readBoolean());
            System.out.print("Enter integer number (Cancel with -1): ");
            input = Input.readInt();
            System.out.println(outputText + input);
        }
    }
}
