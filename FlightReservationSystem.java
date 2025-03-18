import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem {
	public static void main(String[] args) throws FileNotFoundException {

		// Create a FlightManager object
		FlightManager manager = new FlightManager();

		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();    // my flight reservations

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);

			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();
			String flightNum;
			String name;
			String passportNum;
			String seatNum;
			if (action == null || action.equals("")) {
				continue;
			}


			// List all flights
			else if (action.equalsIgnoreCase("LIST")) {
				manager.printAllFlights();
			}
			// Reserve a flight based on Flight number string (example input: res AC220)

			else if (action.equalsIgnoreCase("RES")) {
				// Get the flight number string from the commndLine scanner (use hasNext() to check if there is a
				// flight number string entered
				// call reserveSeatOnFlight() method in manager passing in the flight number string
				// A reference to a Reservation object is returned. Check to make sure it is not == null
				// If it is null, then call manager.getErrorMessage() and print the message
				// If it is not null, add the reservation to the myReservations array list and print the reservation (see class Reservation)

				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
					if (commandLine.hasNext()) {
						name = commandLine.next();
						if (commandLine.hasNext()) {
							passportNum = commandLine.next();
							if (commandLine.hasNext()) {
								seatNum = commandLine.next();

								try{
									Reservation res = manager.reserveSeatOnFlight(flightNum,new Passenger(name,passportNum,seatNum));
									if (res!=null){
										myReservations.add(res);
									}
								}
								catch(FlightNotFoundException fLNF){
									System.out.println(fLNF.getMessage());
								}
								catch(SeatOccupiedException soe){
									System.out.println(soe.getMessage());
								}
								catch(SeatDoesNotExistException sdne){
									System.out.println(sdne.getMessage());
								}
								catch(PassengerAlreadyOnFlightException paoe){
									System.out.println(paoe.getMessage());
								}
								catch(FlightHasNoFirstClassSeatsException e){
									System.out.println(e.getMessage());
								}
							}
						}
					}
				}

			} else if (action.equalsIgnoreCase("SEATS")) {


				if (commandLine.hasNext()) {
					try{
						flightNum = commandLine.next();//getting flight number
						manager.printSeat(flightNum);
					}
					catch(FlightNotFoundException fnfe){
						System.out.println(fnfe.getMessage());
					}

				}
			}
			// Cancel an existing reservation (example input: cancel AC220)
			else if (action.equalsIgnoreCase("PASMAN")) {
				if (commandLine.hasNext()){
					try{
						flightNum = commandLine.next();
						manager.printPassengersForAFlight(flightNum);
					}
					catch(FlightNotFoundException fnfe){
						System.out.println(fnfe.getMessage());
					}catch (FlightEmptyException fee){
						System.out.println(fee.getMessage());
					}
				}

			}

			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			else if (action.equalsIgnoreCase("CANCEL")) {
				/**
				 * cancels a passenger on a flight
				 * needs flight number, passenger name, and passport number
				 * for example, cnclpsngr AC123 Sina 123
				 * cancels the seat of a passenger names Sina with a passport number of 123 on flight AC123
				 */
					if (commandLine.hasNext()) {

						String flightnum = commandLine.next(); //get flight num
						if (commandLine.hasNext()) {
							name = commandLine.next(); // get name
							if (commandLine.hasNext()) {
								String passportnum = commandLine.next(); // get passport number
								try{
									Passenger apassenger = new Passenger(name, passportnum); //create passenger object with name and pass num
									manager.cancelReservation(flightnum,apassenger);
									Reservation remove = new Reservation(flightnum,apassenger);
									for (int i=0;i<myReservations.size();i++){
										if (myReservations.get(i).equals(myReservations)){
											myReservations.remove(i);
										}
									}
								}

								catch(PassengerNotOnFlightException pnof){
									System.out.println(pnof.getMessage());
								}
								catch(FlightNotFoundException e){
									System.out.println(e.getMessage());
								}
								catch (FlightEmptyException e){
									System.out.println(e.getMessage());
								}

								//with flightNum and passenger object
							}
						}
					}
				}

			else if(action.equalsIgnoreCase("MYRES")){
				for (Reservation res:myReservations){
					System.out.println(res.toString());
				}
			}
				System.out.print("\n>");
			}
		}


	}

