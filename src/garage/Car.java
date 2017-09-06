/*
 * Isaiah Forech
 * 6/14/2017
 * Car.java
 */

package garage;

/**
 * Stores the different aspects of each car that will be put into a garage.
 * 
 * @author Isaiah Forech
 * @version 1.0
 * @since 2017-14-06
 */
public class Car {

	String ownerName;
	String model;
	String make;
	int year;
	String date;

	/**
	 * A constructor that will create a new car based on the parameters
	 * 
	 * @param ownerName the name of the owner of the car
	 * @param model the classified model of the car
	 * @param make the brand make of the car that created it
	 * @param year the year the car was made
	 * @param date the data of which the car will have parked at the garage
	 * 
	 */
	public Car(String ownerName, String model, String make, int year, String date) {
		this.ownerName = ownerName;
		this.model = model;
		this.make = make;
		this.year = year;
		this.date = date;
	}

	/**
	 * Prints out the values of into plain text form 
	 */
	@Override
	public String toString() {
		return "\n" + "\n" + ownerName + "'s Car " + "\n" + "Model: " + model + "\n" + "Make: " + make + "\n" + "Year: "
				+ year + "\n" + "(ArrivalStamp): " + date + "\n";
	}
}
