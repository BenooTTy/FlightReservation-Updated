//Parsa Ranjbaran		Student # 501037874
/*
 * A long haul flight is a flight that travels thousands of kilometers and typically has separate seating areas 
 */

public class LongHaulFlight extends Flight
{
	int numFirstClassPassengers;
	String seatType;
	
	// Possible seat types
	public static final String firstClass = "First Class Seat";
	public static final String economy 		= "Economy Seat";  
	

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		// use the super() call to initialize all inherited variables
		// also initialize the new instance variables
		super(flightNum,airline,dest,departure,flightDuration,aircraft);
		seatType = economy; //set seat type to economy
		numFirstClassPassengers = 0; //set number of passengers to 0.
	}

	public LongHaulFlight()
	{
     // default constructor
		this.flightNum = "AC100";
		this.airline = "Air Canada";
		this.dest = "New York";
		this.origin = "Toronto";
		this.departureTime = "22:30";
		this.flightDuration = 150;
		this.aircraft = new Aircraft(300,0,"Boeing 737");
		this.seatType = economy;
		this.numFirstClassPassengers = 0;
	}
	

	public boolean reserveSeat()
	{
		/**
		 * @ param none
		 * the method overrides the reserve seat method inherited from class flight
		 * Inside of it, we call the reserveSEat method defined below and give it a parameter of economy
		 * Therefore, we only use this reserveSeat method when we want to reserve a seat for an economy seat
		 *
		 */
		// override the inherited reserveSeat method and call the reserveSeat method below with an economy seatType
		// use the constants defined at the top
		return reserveSeat(economy);
	}

	/*
	 * Reserves a seat on a flight. Essentially just increases the number of passengers, depending on seat type (economy or first class)
	 */
	public boolean reserveSeat(String seatType)
	{
		/**
		 * @ param : String seatType
		 * if the seatType is economy we can just use the inherited seattype method which
		 * increases number of passengers by 1 if seats are available and return true
		 * if it is first class, we will first check if there are first class seats available
		 * if there are first class seats available, we increment first class passengers by 1 and return true
		 */
		// if seat type is economy 
		//			call the superclass method reserveSeat() and return the result
		// else if the seat type is first class then 
		// 			check to see if there are more first class seats available (use the aircraft method to get the max first class seats
		// 			of this airplane
		//    	if there is a seat available, increment first class passenger count (see instance variable at the top of the class)
		//    	return true;
		// else return false
		

		/*if (seatType==economy ){
			return super.reserveSeat();
		}*/
		/*else if(seatType==firstClass){
			if ((this.aircraft.getNumFirstClassSeats()-this.numFirstClassPassengers)>0){//are first class seats available
				this.numFirstClassPassengers++;//if they are increment firstclasspassengers by 1
				return true;
			}
		}*/
		return false;//return false if the seat type is neither of the two

	}
	
	// Cancel a seat 
	public void cancelSeat()
	{
		/**
		 * if the method is called by no parameter is passed in, that means we want to cancel an economy seat
		 * we will just call the method below and pass in a parameter of economy
		 */
	  // override the inherited cancelSeat method and call the cancelSeat method below with an economy seatType
		// use the constants defined at the top
		cancelSeat(economy);
	}
	
	public void cancelSeat(String seatType)
	{
		// if seat type is first class and first class passenger count is > 0
		//  decrement first class passengers
		// else
		// decrement inherited (economy) passenger count
		if (seatType==firstClass && numFirstClassPassengers>0){//if the seattype is firstclass and there are any first class
				//passengers at all, cancel one of their seats
			numFirstClassPassengers--;
		}
		else{
			passengers--;//cancel an economy seat
		}
	}
	// return the total passenger count of economy passengers *and* first class passengers
	// use instance variable at top and inherited method that returns economy passenger count
	public int getPassengerCount()
	{
		/**
		 * the total number of first class students is added to the inherited method get passengers which returns the
		 * 		number of economy passengers
		 */
		return numFirstClassPassengers+super.getPassengers();
	}

	public String toString(){
		/**
		 * overrides toString() from flight
		 * adds the phrase ' long haul' to the end
		 * returns a string version of the flight
		 */
		return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status + "		Long haul";
	}
	public FlightType getFlightType(){return FlightType.LONGHAUL;}

}
