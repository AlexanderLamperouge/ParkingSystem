/**
 * Represents a parking lot that manages parking spaces.
 */
public class ParkingLot {
    private ParkingSpace parkingSpace; // Encapsulates the parking space management

    /**
     * Constructs a ParkingLot instance and initializes its parking space
     * management.
     */
    public ParkingLot() {
        this.parkingSpace = new ParkingSpace(); // Initialize parking space management
    }

    /**
     * Attempts to park a car in the parking lot.
     * 
     * @param enterCar The car attempting to enter the parking lot.
     * @return true if the car is successfully parked, otherwise false.
     *         Note: Current implementation always returns true. Consider
     *         implementing logic to check parking space availability.
     */
    public boolean enter(Car enterCar) {
        boolean flag;
        flag = this.parkingSpace.saveCar(enterCar); // Saves car to a parking space
        if (flag == true) {
            System.out.println("Save Successfully!");
            return true;
        } else {
            System.out.println("Error!");
            return false;
        }
        // Indicates car was successfully parked
    }

    /**
     * Attempts to remove a car from the parking lot based on its license plate.
     * 
     * @param licensePlate The license plate of the car to be removed.
     * @return true if the car is successfully removed, otherwise false.
     *         Note: Current implementation always returns true. Consider
     *         implementing logic to actually check and remove the car.
     */
    public boolean leave(String licensePlate) {
        boolean flag;
        Car leavecar = this.parkingSpace.findCar(licensePlate);
        flag = this.parkingSpace.leaveCar(leavecar);
        // Implementation needed to remove the car based on license plate
        if (flag == true) {
            System.out.println("Leave Successfully");
            return true;
        } else {
            System.out.println("Error!");
            return false;
        }
        // Placeholder return value
    }

    /**
     * Finds and prints the parking space of a specific car.
     * 
     * @param carNumber The license plate number of the car to locate.
     *                  Note: This is a placeholder implementation. Actual logic to
     *                  locate the car's space needs to be implemented.
     */
    public void getLocation(String carNumber) {
        // Placeholder implementation; needs logic to find and print the car's parking
        // space
        Car yourCar = parkingSpace.findCar(carNumber);

        if (yourCar != null) {
            Location yourLocation = yourCar.getLocation();

            int yourFloor = yourLocation.getFloor() + 1;
            String yourArea = yourLocation.getArea();
            int yourNumber = yourLocation.getSlot();

            System.out.println(
                    "The car is located at Floor " + yourFloor + ", Area " + yourArea + ", Slot " + yourNumber + ".");
        } else {
            System.out.println("Don't have this car!!!");
        }
    }

    public Car findCar(String carNumber) {
        // Placeholder implementation; needs logic to find and print the car's parking
        // space
        Car yourCar = parkingSpace.findCar(carNumber);
        return yourCar;
    }

    public void displayFreeSpace() {
        parkingSpace.displayFreeSpace();
    }

    public void displayCar() {
        parkingSpace.displayParkedCars();
    }
}
