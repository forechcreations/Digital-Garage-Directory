/*
 * Isaiah Forech
 * 6/14/2017
 * Garage.java
 */

package garage;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import garage.Save;

/**
 * Emulates a digital garage. A user will use it to organize when and who parked
 * their car into the garage which represents limited space. It will also record
 * when the car arrived and when the car departed from the garage. This may be
 * useful in many real life scenarios.
 * 
 * @author Isaiah Forech
 * @version 1.0
 * @since 2017-14-06
 */
public class Garage {

	// Create tree map to store menu
	private static TreeMap<Integer, String> menu = new TreeMap<Integer, String>();

	// Declare the Writer
	private static PrintWriter writer = null;

	// maximum garage space
	private static int maxGarageSpace = 3;

	/**
	 * The main method that will run the program. Makes use of a switch statement which 
	 * allows a variable to be tested for equality against a list of values, which allows
	 * a flow of control for the program. Each choice will create different actions.
	 * 
	 * @param args
	 *            unused
	 * 
	 */
	public static void main(String args[]) {

		// Store choices into the menu
		menu.put(1, ". Enter Car into the Garage");
		menu.put(2, ". Retrieve Car from the Garage");
		menu.put(3, ". Display Cars");
		menu.put(4, ". Exit Program");

		// Create an array list that will store the cars, and the amount of cars
		ArrayList<Count> garageSpace = new ArrayList<Count>();
		ArrayList<Car> garageParking = new ArrayList<Car>();

		Scanner in = new Scanner(System.in);

		System.out.println("*************************");
		System.out.println("Welcome to the Garage");

		// The user will be asked for input and is stored into select
		int select;
		select = 0;

		// Stores where the car will be parked
		int parkCar;

		try {

			// Initialize a print writer so data can be printed to a text file
			String writeFile = new String("car_files/temp.txt");
			writer = new PrintWriter(new FileOutputStream(writeFile));

			while (true) {

				// Print Out the Menu
				for (Integer key : menu.keySet()) {
					System.out.println(key + " " + menu.get(key));
				}

				System.out.println("*************************");

				// User selects which menu choice shall be initialized
				select = in.nextInt();
				switch (select) {

				case 1:
					// When the available size in the garage isn't full
					if (garageSpace.size() <= maxGarageSpace) {

						int numOfCars = 0;
						Count vehicle = null;

						// Count of the size of the garage space and assign it to a vehicle value
						for (int i = 0; i < garageSpace.size(); i++) {
							vehicle = garageSpace.get(i);
							numOfCars = vehicle.ID;

						}
						// Prompt the user how many cars are currently parked
						System.out.println("Number of cars parked: " + numOfCars);
						System.out.println("");

						// Prompt user how many parking spots are available
						System.out.println("There are only " + (4 - numOfCars) + " parking spots available,");

						// Prompt user a guest is ready to enter the garage
						System.out.println("Another Guest is ready to enter the garage");
						System.out.println("");

						// The space of the Garage adding another car is stored into a variable which is
						// parking
						parkCar = garageSpace.size() + 1;

						// Verifying it is adding another car
						if ((garageSpace.size() + 1) == parkCar) {

							// Add the single new car to the garage
							garageSpace.add(new Count(parkCar));

							// Enter details of the person parking car
							
							// Console will be open to provide use of adding multiple cars
							@SuppressWarnings("resource")
							Scanner console = new Scanner(System.in);
							
							String name;
							System.out.println("Enter name of car owner: ");
							name = console.nextLine();

							String make;
							System.out.println("Enter make of the car (ex. Ford, Toyota, etc): ");
							make = console.nextLine();

							String model;
							System.out.println("Enter the model: ");
							model = console.nextLine();

							int year;
							System.out.println("Enter the year: ");
							year = console.nextInt();

							String currentDate;
							currentDate = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

							// Add the new guest and their specifications to the program
							garageParking.add(new Car(name, model, make, year, currentDate));
							System.out.println("");

						}

					} else {

						System.out
								.println("There is currently no space to park, please wait for another car to leave.");

					}

					break;

				case 2:

					// If there are no cars parked the program will reprompt
					if (garageSpace.size() == 0) {
						System.out.println("* There are currently no cars parked");
						break;
					} else {

						// Remove car from garage
						System.out.println("Choose the car you would like to retrieve: ");

						// Store total cars into a variable
						int totalCars = 0;

						// Display the list of cars in which to remove
						for (Car line : garageParking) {
							totalCars++;
							System.out.println(totalCars + ". " + line.ownerName + "'s Car");
						}

					}

					// User will choose which car to remove
					int RemoveCar = 0;
					RemoveCar = in.nextInt();
					int carSelect = RemoveCar - 1;

					// Print to the text file when the car left and when it arrived
					writer.println(garageParking.get(carSelect).toString() + "(DepartureStamp): "
							+ ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

					// Remove the selected car
					garageParking.remove(carSelect);
					garageSpace.remove(carSelect);

				case 3:

					int parkCount = 0;
					int numOfCars = 0;

					// Print out each car listed from the garage
					for (Car car : garageParking) {
						parkCount += 1;
						numOfCars++;
						System.out.println("Car " + parkCount + ": " + car.toString() + "(DepartureStamp): " + "\n");
					}

					// Show the number of cars currently stored in the garage
					System.out.println("");
					System.out.println("Number of Cars in Garage : " + numOfCars);
					System.out.println("");

					break;

				case 4:

					// If there is space in the garage, the user can't exit the program until garage
					// is empty.
					if (garageSpace.size() >= 1) {
						System.out
								.println("* You can not exit the program if there are cars still parked in the garage");
						System.out.println("");
						break;
					} else {
						System.out.println("Thank you for using our Garage! ");

						if (writer != null) {
							writer.close();
						}

						// Append Data
						Save.saveData();
						System.exit(2);
						in.close();
					}

				}

			}
		} catch (Exception e) {
			// If the user doesn't type a matching protocol then restart the program
			System.out.println("");
			System.out.println(e + "* You have entered a wrong number of character, please try again");
			System.out.println("");
			Garage.main(args);
		}
	}

}
