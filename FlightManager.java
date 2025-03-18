import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class FlightManager
{
    // Contains list of Flights departing from Toronto in a single day
    TreeMap<String,Flight> flightsMap = new TreeMap<String,Flight>(); //arraylist to hold flights
    // Contains list of available airplane types and their seat capacity
    ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();

    String errorMsg = null; // if a method finds an error (e.g. flight number not found) set this string. See video!

    Random random = new Random(); // random number generator - google "Java class Random". Use this in generateFlightNumber
    File flights = new File("flights.txt");
    Scanner sc = new Scanner(flights);

    public FlightManager() throws FileNotFoundException {
        // DO NOT ALTER THIS CODE - THE TA'S WILL USE IT TO TEST YOUR PROGRAM
        // IN ASSIGNMENT 2 YOU WILL BE LOADING THIS INFORMATION FROM A FILE
        airplanes.add(new Aircraft(4,0,"Aircraft with 4 seats"));
        airplanes.add(new Aircraft(16,0,"Aircraft with 16 seats"));
        airplanes.add(new Aircraft(20, 0,"Boeing 737"));
        airplanes.add(new Aircraft(40,0,"Airbus 320"));
        airplanes.add(new Aircraft(60, 0,"Dash-8 100"));
        airplanes.add(new Aircraft(80, 0,"Bombardier 5000"));
        airplanes.add(new Aircraft(100, 12, "Boeing 747"));
        // Create some aircraft types with max seat capacities
        flightsMap = new TreeMap<String,Flight>();
        File file = new File("flights.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] words = line.split("\\W+");

            Flight flightToAdd;
            String airline = words[0];
            String flightnum = generateFlightNumber(airline);
            String dest = words[1];
            String departure = words[2];
            int minSeats = Integer.parseInt(words[3]);
            int duration = Integer.parseInt(words[4]);
            Aircraft aircraft = new Aircraft(0,0,"");
            for (Aircraft plane:airplanes){
                if (plane.getTotalSeats()>=minSeats){
                    aircraft = plane;
                    break;
                }
            }
            if (dest.equals("Tokyo")){
                flightsMap.put(flightnum,new LongHaulFlight(flightnum,airline,dest,departure,duration,aircraft));
            }
            else{
                flightsMap.put(flightnum,new Flight(flightnum,airline,dest,departure,duration,aircraft));
            }
        }
    }

    /*
     * This private helper method generates and returns a flight number string from the airline name parameter
     * For example, if parameter string airline is "Air Canada" the flight number should be "ACxxx" where xxx is
     * a random 3 digit number between 101 and 300 (Hint: use class Random - see variable random at top of class)
     * you can assume every airline name is always 2 words.
     *
     */
    private String generateFlightNumber(String airline)
    {
        /**
         * @ param : String airline
         * Given an airline, we generate a random number beween 101 and 300
         * Then, we will add that number to the two letter short version of the airline and return a string as the flight number
         */
        // Your code here
        int randomNum = 101 + (int)(Math.random() * 201);//generating a random flight number between 101 and 300
        //we have to add 101 to a random number from 0 to 200
        String flightnumrand = Integer.toString(randomNum);//we turn the random number to a string so that we can
        //concacenate the string
        if (airline.equals("Air_Canada")){
            return "AC" + flightnumrand;
        }
        else if (airline.equals("United_Airlines")) {
            return "UA" + flightnumrand;
        }
        else if (airline.equals("Porter_Airlines")){
            return "PA" + flightnumrand;
        }
        return null;

    }
    // Prints all flights in flights array list (see class Flight toString() method)
    // This one is done for you!
    public void printAllFlights()
    {
        /**
         * print all flights by going through the flights arraylist and printing the string version of each flight
         */
        for (String key : flightsMap.keySet()){
            System.out.println(flightsMap.get(key).toString());
        }
    }

    // Given a flight number (e.g. "UA220"), check to see if there are economy seats available
    // if so return true, if not return false

    public void printSeat(String flightNum) throws FlightNotFoundException{
        boolean found = false;
        for (String key : flightsMap.keySet()){
           if (flightsMap.get(key).flightNum.equals(flightNum)){
               flightsMap.get(key).printSeats();
               found =true;
           }
       }
       if (found == false){
           throw new FlightNotFoundException("Could not Find Flight: " + flightNum);
       }
    }


    // Given a flight number string flightNum and a seat type, reserve a seat on a flight
    // If successful, return a Reservation object
    // NOTE: seat types are not used for basic Flight objects (seats are all "Economy Seats")
    // class LongHaulFlight defines two seat types
    // I  suggest you first write this method *without* considering class LongHaulFlight
    // once that works (test it!!), add the long haul flight code
    public Reservation reserveSeatOnFlight(String flightNum,Passenger aPassenger) throws FlightHasNoFirstClassSeatsException, FlightNotFoundException,SeatOccupiedException,SeatDoesNotExistException,PassengerAlreadyOnFlightException {
        /**
         * @ param : String flightNum
         * @ param : Passenger aPassenger
         * if we can find the flight num and the flight does not contain that passenger, we will reserve a seat for them
         * @ return : reservation
         */
        Flight aflight;


        String seat = aPassenger.seatNum;
        int row = 0;
        int column = seat.charAt(0)-'1';
        char letter = seat.charAt(1);
        switch (letter){
            case 'A':
                row=0;
                break;
            case 'B':
                row=1;
                break;
            case 'C':
                row=2;
                break;
            case 'D':
                row=3;
                break;
        }

        if (flightsMap.containsKey(flightNum)){
            flightsMap.get(flightNum).reserveSeat(aPassenger);
            return new Reservation(flightNum,aPassenger);
        }
        else{
            throw new FlightNotFoundException("Could not Find Flight " + flightNum);

        }





    }

    public String getErrorMessage()
    {
        return errorMsg;
    }

    /*
     * Given a Reservation object, cancel the seat on the flight
     */
    public void cancelReservation(String flightnum,Passenger aPassenger)throws FlightEmptyException,PassengerNotOnFlightException,FlightNotFoundException
    {
        // Get the flight number string from res
        // Search flights to find the Flight object - if not found, set errorMsg variable and return false
        // if found, cancel the seat on the flight (see class Flight)
        Flight aflight;
        boolean done = false;
        for (String key:flightsMap.keySet()){
            if (key.equals(flightnum)){
                aflight = flightsMap.get(key);
                aflight.cancelSeat(aPassenger);
                done = true;

                }

            }
        if (done == false){
            throw new FlightNotFoundException("Could not find flight "+ flightnum);
        }


        // if we get here, that means we couldn't find the flight




    }
    // Sort the array list of flights by increasing departure time
    // Essentially one line of code but you will be making use of a Comparator object below



    //Sort the array list of flights by increasing flight duration
    // Essentially one line of code but you will be making use of a Comparator object below

    // Prints all aircraft in airplanes array list.
    // See class Aircraft for a print() method


    // Sort the array list of Aircraft objects
    // This is one line of code. Make sure class Aircraft implements the Comparable interface
    public void sortAircraft()
    {
        Collections.sort(airplanes);
    }


    public void printPassengersForAFlight(String flightnum) throws FlightNotFoundException,FlightEmptyException{
        boolean found =false;
        for (String str : flightsMap.keySet()){
            if (str.equals(flightnum)){
                flightsMap.get(str).printPassengers();
                found = true;
            }
        }
        if (found == false){
            throw new FlightNotFoundException("Could not find flight: " + flightnum);
        }
    }


}
