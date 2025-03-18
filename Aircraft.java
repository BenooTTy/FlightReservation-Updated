/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of first class seats
 * 
 */
public class Aircraft implements Comparable<Aircraft> // implements the comparable interface
{
	int numEconomySeats;
	int numFirstClassSeats;
	String model;
	String[][] seatLayout;

	public Aircraft(int economy, int firstClass, String aModel) {
		String[] letters = { "A", "B", "C", "D" };
		this.numEconomySeats = economy;
		this.numFirstClassSeats = firstClass;
		this.model = model;
		int totSeat = economy + firstClass;
		if (totSeat <= 16) {
			seatLayout = new String[2][totSeat / 2];
		} else {
			seatLayout = new String[4][totSeat / 4];
		}
		for (int i = 0; i < seatLayout.length; i++) {
			for (int j = 0; j < seatLayout[i].length; j++) {
				seatLayout[i][j] = Integer.toString(j + 1) + letters[i];
			}
		}
		if (firstClass > 0) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					seatLayout[i][j] += "+";
				}
			}
		}
	}

	public int getNumSeats() {
		return numEconomySeats; // returns number of economy seats
	}

	public int getTotalSeats() {
		return numEconomySeats + numFirstClassSeats; // returns number of total seats
	}

	public int getNumFirstClassSeats() {
		return numFirstClassSeats; // returns number of first class seats
	}

	public String getModel() {
		return model;
	} // returns aircraft model

	public void setModel(String model) {
		this.model = model;
	} // set the model of the aircraft using this method

	public void print() {
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: "
				+ numFirstClassSeats);
	}
	// prints some information about the aircraft

	/*
	 * Write a compareTo method that is part of the Comparable interface
	 */
	public int compareTo(Aircraft otherAircraft) {// compares an aircraft to another aircraft by the number of seats
		if (this.getNumSeats() == otherAircraft.getNumSeats()) {// if their regular seats are equal, compare their first
																// class seats
			return this.getNumFirstClassSeats() - otherAircraft.getNumFirstClassSeats();
		}
		return this.getNumSeats() - otherAircraft.getNumSeats();// else sort based on whichever has higher number of
																// seats
	}

}
