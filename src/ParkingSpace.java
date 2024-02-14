import java.util.Formatter;

/**
 * Manages the parking spaces within a parking lot, tracking capacity and
 * occupancy.
 */
public class ParkingSpace {
    private final int capacity; // Maximum number of cars that can be parked
    private int usedParkingSlots; // Current number of occupied parking slots
    private Car[][][] parkingSpace; // 3D array to represent parking slots across different levels

    /**
     * Initializes a ParkingSpace instance with predefined capacity and empty
     * parking slots.
     */
    public ParkingSpace() {
        this.capacity = 500; // Set total capacity
        this.usedParkingSlots = 0; // Initialize used slots as 0
        parkingSpace = new Car[5][5][20]; // Initialize parking space structure (e.g., 5 floors, 5 areas per floor, 20
                                          // slots per area)
    }

    /**
     * Attempts to park a car, increasing the used parking slot count if successful.
     * 
     * @param enterCar The car attempting to be parked.
     *                 Note: This method currently does not place the car into a
     *                 specific slot in the array.
     */
    public boolean saveCar(Car enterCar) {
        if (usedParkingSlots < capacity) {

            Location myLocation = enterCar.getSpace();
            int myFloor = myLocation.getFloor();
            int myArea = myLocation.getArea();
            int myNumber = myLocation.getNumber();

            if (parkingSpace[myFloor][myArea][myNumber] == null) {
                parkingSpace[myFloor][myArea][myNumber] = enterCar;
                System.out.println("Parking success!"); // Confirm parking success
                this.usedParkingSlots++; // Increment used slots
                return true;
            } else {
                return false;// This location has car!!!
            }
        } else {
            System.out.println("Parking failed!"); // Indicate parking failure due to capacity limits
            return false;// The ParkingSpace is full!!!
        }
    }

    /**
     * Attempts to remove a car from the parking lot, decreasing the used parking
     * slot count if successful.
     * 
     * @param leaveCar The car attempting to leave.
     * @return true if the car is successfully removed, false if the car is not
     *         found.
     *         Note: Actual car removal logic and search within the array is not
     *         implemented.
     */
    public boolean leaveCar(Car leaveCar) {
        // Placeholder logic for car removal
        boolean flagOfCar = true; // Assume car is present for demonstration

        if (flagOfCar) {
            this.usedParkingSlots--; // Decrement used slots
            return true; // Indicate successful removal
        } else {
            System.out.println("Parking Space doesn't have this car!"); // Car not found
            return false;
        }
    }

    /**
     * Finds and returns the location of a specified car.
     * 
     * @param carNumber The license plate number of the car to find.
     * @return A string representing the location of the car.
     *         Note: This method currently returns a placeholder location.
     */
    public String findCar(String carNumber) {
        // Placeholder implementation; actual search logic to find the car's location is
        // needed
        for (int floor = 0; floor < parkingSpace.length; floor++) {
            for (int area = 0; area < parkingSpace[floor].length; area++) {
                for (int slot = 0; slot < parkingSpace[floor][area].length; slot++) {
                    Car car = parkingSpace[floor][area][slot];
                    if (car != null && car.getCarNumber().equals(carNumber)) {
                        return "The car is located at Floor " + (floor + 1) + ", Area " + (area + 1) + ", Slot " + (slot + 1) + ".";
                    }
                }
            }
        }
        return "The car is not found. Maybe the wrong input.";
//        return "The location of car."; // Example output
    }

    public void displayFreeSpace() {
        /*
         * find all or the available space for user
         */
        System.out.println("Available Free Parking Spaces:");

        // Table header
        System.out.println("+-------+-------+----------------------------------------------------+");
        System.out.println("| Floor | Area  |                       Slot                         |");
        System.out.println("+-------+-------+----------------------------------------------------+");

        // Table body
        for (int floor = 0; floor < parkingSpace.length; floor++) {
            for (int area = 0; area < parkingSpace[floor].length; area++) {
                String slot_list = "";

                for (int slot = 0; slot < parkingSpace[floor][area].length; slot++) {
                    if(parkingSpace[floor][area][slot] == null)
                        slot_list = slot_list.concat(String.valueOf(slot)) + " ";
                }
                try (Formatter formatter = new Formatter()) {
                    formatter.format("| %-5d | %-5d | %s |%n", (floor), (area), (slot_list));
                    System.out.print(formatter);
                }
            }
        }

        System.out.println("+-------+-------+-------+");

    }
}