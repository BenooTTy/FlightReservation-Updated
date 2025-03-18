//Parsa Ranjbaran		Student # 501037874
/*
 * A simple class to model an electronic airline flight reservation
 * 
 * This class has been done for you
 */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	String passengerName;
	String passengerPassport;
	String seat;
	
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
	}

	public Reservation(String flightnum,Passenger aPassenger){
		flightNum = flightnum;
		this.flightInfo = " ";
		this.firstClass = false;
		this.passengerName = aPassenger.getName();
		this.passengerPassport = aPassenger.getPassportNum();
		this.seat = aPassenger.getSeatNum();
	}

	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo);
	}

	public boolean equals(Reservation one, Reservation two){
		/**
		 * @ param : two reservation objects
		 * Returns true if the one and two have the same flight num, name, and passport number
		 */
		 if (one.flightNum.equals(two.flightNum) && one.passengerName.equals(two.passengerName)){
		 	return true;
		 }
		 return false;
	}

	public String toString(){
		return "Flight Number: " + flightNum + "\t" + "Name: " + passengerName + "\t" + "Passport Number: " +  passengerPassport + "\t" + " Seat Number: " +seat;
	}

}
