package hsb.mkss1.order_system.adapters.cli.util;

import java.io.BufferedReader;
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
}
