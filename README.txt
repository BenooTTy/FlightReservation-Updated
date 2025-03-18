Flight Reservation System - README

🎯 Overall Purpose:
This system simulates a "flight booking" system where:
- Flights originate from "Toronto" and go to various destinations.
- Users can interact with both economy and first-class seating options.
- The system allows users to:
  - List available flights and aircraft.
  - Reserve seats (economy or first class).
  - Cancel seat reservations.
  - View passengers aboard specific flights.
  - Manage personal reservations saved locally.

---

🗂 Major Classes Breakdown

1️⃣ Aircraft.java
- Represents aircraft and its seat configuration (economy and first class).
- Implements the `Comparable` interface to allow sorting by seat capacity.

2️⃣ Flight.java
- Models a regular flight with departure details and destination.
- Manages an `ArrayList<Passenger>` to track economy passengers.
- Provides methods to reserve or cancel seats and convert departure times to minutes for sorting.

3️⃣ LongHaulFlight.java
- Inherits from `Flight` and supports long-haul flights with both economy and first-class seats.
- Customizes seat reservation and cancellation logic.
- Marks flight type as `"Long haul"` in `toString()` method.

4️⃣ Passenger.java
- Models individual passengers with details like name, passport number, and seat number.
- Implements a custom `equals()` method for identifying passengers uniquely.
- Simple `toString()` for easy display and logging.

5️⃣ Reservation.java
- Represents a reservation record, tracking the flight number and whether the passenger reserved a first-class seat.
- Provides simple print functionality for reservation details.

6️⃣ FlightManager.java
- The core service class managing the flight system.
- Handles a collection of `Flight` and `LongHaulFlight` objects, as well as `Aircraft` objects.
- Manages seat reservations, cancellations, sorting of flights by departure time, and passenger tracking.
- Implements error handling and maintains flight and aircraft details.

7️⃣ FlightReservationSystem.java
- Command-line interface (CLI) for interacting with the system.
- Accepts commands like `LIST`, `RES`, `CANCEL`, `MYRES`, etc.
- Communicates with `FlightManager` to manage reservations and list available flights.
- Maintains a personal reservation list locally.

---

🛠 How It Works Together
1. The **CLI** in `FlightReservationSystem` interacts with `FlightManager`, which handles:
   - Flight management (both regular and long-haul).
   - Seat reservations and cancellations.
   - Sorting and managing aircraft data.
   
2. **FlightManager** handles the business logic of managing flights, passengers, and seat availability.

3. **Flight** and **LongHaulFlight** handle seat logic based on the aircraft's capacity (economy and first class).

4. User reservations are stored both in the system (`FlightManager`) and locally on the CLI session (`myReservations`).

---

🏃 How to Run

1. Ensure that all 7 Java files are in the same directory.
2. Open the terminal and compile the Java files:
   javac *.java

3. Run the system:
   java FlightReservationSystem

4. The system will display a `>` prompt waiting for user input.

---

🖥 Available CLI Commands

| Command                                | Description                                                   |
|----------------------------------------|---------------------------------------------------------------|
| `LIST`                                 | Lists all available flights.                                  |
| `CRAFT`                                | Lists all available aircraft.                                 |
| `RES flightNum`                        | Reserve an economy seat on a flight.                          |
| `RESPSNGR flightNum name passportNum`  | Reserve a seat for a passenger.                               |
| `RESFCL flightNum`                     | Reserve a first-class seat on a long-haul flight.             |
| `SEATS flightNum`                      | Check seat availability on a flight.                          |
| `CANCEL flightNum`                     | Cancel a reservation from your personal list.                 |
| `CNCLPSNGR flightNum name passportNum` | Cancel a passenger’s seat directly from a flight.             |
| `MYRES`                                | Display your personal reservations.                           |
| `SORTCRAFT`                            | Sort and list aircraft by seat count.                         |
| `SORTBYDEP`                            | Sort flights by departure time.                               |
| `SORTBYDUR`                            | Sort flights by duration.                                     |
| `QUIT`                                 | Exit the program.                                             |

---
