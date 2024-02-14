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

            Location myLocation = enterCar.getLocation();
            int myFloor = myLocation.getFloor();
            int myArea = myLocation.getAreaNumber();
            int mySlot = myLocation.getSlot();

            if (parkingSpace[myFloor][myArea][mySlot] == null) {
                parkingSpace[myFloor][myArea][mySlot] = enterCar;
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
        // String flag = findCar(leaveCar.getCarNumber()); // boolean
        // Location car_location = leaveCar.getSpace();
        Location myLocation = leaveCar.getLocation();

        int myFloor = myLocation.getFloor();
        int myArea = myLocation.getAreaNumber();
        int myNumber = myLocation.getSlot();

        if (parkingSpace[myFloor][myArea][myNumber] == null) {
            System.out.println("Parking Space doesn't have this car!"); // Car not found
            return false;
        } else {
            this.usedParkingSlots--; // Decrement used slots
            parkingSpace[myFloor][myArea][myNumber] = null;
            return true; // Indicate successful removal
        }
    }

    /**
     * Finds and returns the location of a specified car.
     * 
     * @param carNumber The license plate number of the car to find.
     * @return A string representing the location of the car.
     *         Note: This method currently returns a placeholder location.
     */
    public Car findCar(String carNumber) {
        // Placeholder implementation; actual search logic to find the car's location is
        // needed
        for (int floor = 0; floor < parkingSpace.length; floor++) {
            for (int area = 0; area < parkingSpace[floor].length; area++) {
                for (int slot = 0; slot < parkingSpace[floor][area].length; slot++) {
                    Car car = parkingSpace[floor][area][slot];
                    if (car != null && car.getCarNumber().equals(carNumber)) {
                        return parkingSpace[floor][area][slot];
                    }
                }
            }
        }
        return null;
    }

    public void displayFreeSpace() {
        System.out.println("That's the free space.");
    }
}