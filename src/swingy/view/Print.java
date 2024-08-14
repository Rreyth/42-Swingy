package swingy.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Print {
	public static void print(Object p_message) {
		System.out.println(p_message);
	}

	public static void printErr(Object p_message) {
		System.err.println(p_message);
	}

	public static void printToFile(String p_message, String path) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(p_message);
		}
		catch (IOException e) {
			printErr("Error writing to file: " + e.getMessage());
		}
	}
}