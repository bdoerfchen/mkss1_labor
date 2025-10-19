package hsb.mkss1.ordersystem.util;

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
		} catch(NumberFormatException e) {
			result = false;
		}

		return result;
	}

	public static double readDouble() {
		double result;
		try {
			result = Double.parseDouble(readString());
		} catch(NumberFormatException e) {
			result = 0d;
		}

		return result;
	}

	public static float readFloat() {
		float result;
		try {
			result = Float.parseFloat(readString());
		}
		catch(NumberFormatException e) {
			result = 0f;
		}

		return result;
	}

	public static int readInt() {
		int result = 0;
		String v = null;
		try {
			v = readString();
			result = Integer.decode(v);
		} catch(NumberFormatException e) {
			return 0;
		}

		return result;
	}

	public static String readString() {
		String result;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			result = in.readLine();
		} catch(IOException e) {
			result = "";
		}
		return result;
	}

	// Only for test purposes
	static void main() {
		int input = 0;
		while(input != -1) {
			IO.print("Enter text: ");
			IO.println("hsb.mkss1.ordersystem.util.Input was:" + Input.readString());
			IO.print("Enter float: ");
			IO.println("hsb.mkss1.ordersystem.util.Input was:" + Input.readFloat());
			IO.print("Enter double: ");
			IO.println("hsb.mkss1.ordersystem.util.Input was:" + Input.readDouble());
			IO.print("Enter boolean: ");
			IO.println("hsb.mkss1.ordersystem.util.Input was:" + Input.readBoolean());
			IO.print("Enter integer number (Cancel with -1): ");
			input = Input.readInt();
			IO.println("hsb.mkss1.ordersystem.util.Input was: " + input);
		}
	}
}
