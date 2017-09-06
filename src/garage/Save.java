/*
 * Isaiah Forech
 * 6/14/2017
 * Save.java
 */

package garage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the class that will save the garage's car history to a text file
 * 
 * @author Isaiah Forech
 * @version 1.0
 * @since 2017-14-06
 */
public class Save {

	public static void saveData() {

		// Copy data_flush to history.txt
		appendTo("car_files/temp.txt", "car_files/records.txt");
	}

	/**
	 * The appendTo method will pass the source data and send it to the output file
	 * 
	 * @exception FileNotFoundException
	 *                if the files specified can't be read or found, that means
	 *                there was an error trying to copy the files.
	 * 
	 */
	public static void appendTo(String source, String destination) {

		// passing true to the FileOutputStream constructor means "append mode"
		try (Scanner reader2 = new Scanner(new FileInputStream(source));
				PrintWriter writer2 = new PrintWriter(new FileOutputStream(destination, true))) {

			while (reader2.hasNextLine()) {
				String line = reader2.nextLine();
				writer2.println(line);
				writer2.println("");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error copyng file from " + source + " to " + destination);
		}
	}
}
