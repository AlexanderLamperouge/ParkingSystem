import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                // System.out.println("Parking success!"); // Confirm parking success
                this.usedParkingSlots++; // Increment used slots
                return true;
            } else {
                return false;// This location has car!!!
            }
        } else {
            // System.out.println("Parking failed!"); // Indicate parking failure due to
            // capacity limits
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
        System.out.println("Available Free Parking Spaces:");

        // Table header
        System.out.println("+-------+-------+----------------------+");
        System.out.println("| Floor | Area  |         Slot         |");
        System.out.println("+-------+-------+----------------------+");

        // Table body
        for (int floor = 0; floor < parkingSpace.length; floor++) {
            for (int area = 0; area < parkingSpace[floor].length; area++) {
                StringBuilder slotList = new StringBuilder();
                char areaChar = (char) (area + 'A');

                int start = -1; // Start of a sequence of free slots
                for (int slot = 0; slot < parkingSpace[floor][area].length; slot++) {
                    // If slot is free and start is -1, we start a new sequence
                    if (parkingSpace[floor][area][slot] == null && start == -1) {
                        start = slot;
                    }

                    // If slot is not free or it's the last slot and we have a sequence started
                    if ((parkingSpace[floor][area][slot] != null || slot == parkingSpace[floor][area].length - 1)
                            && start != -1) {
                        // If it's the end of a sequence or the last slot, append the sequence to
                        // slotList
                        if (slot - start > 0 || (slot == parkingSpace[floor][area].length - 1
                                && parkingSpace[floor][area][slot] == null)) {
                            slotList.append(String.format("%d-%d, ", start, slot == parkingSpace[floor][area].length - 1
                                    && parkingSpace[floor][area][slot] == null ? slot : slot - 1));
                        } else { // Single free slot
                            slotList.append(String.format("%d, ", start));
                        }
                        start = -1; // Reset start for the next sequence
                    }
                }

                // Remove trailing comma and space
                if (slotList.length() > 0) {
                    slotList.setLength(slotList.length() - 2);
                }

                System.out.printf("| %-5d | %-5c | %-20s |%n", (floor + 1), areaChar, slotList.toString());
            }
        }

        System.out.println("+-------+-------+----------------------+");
        System.out.println("That's the free space.");
    }

    /**
     * Displays information about all parked cars, sorted by floor.
     */
    public void displayParkedCars() {
        // Use a Map to group cars by floor
        Map<Integer, List<String>> carsByFloor = new HashMap<>();

        // Collect all parked cars and group them by floor
        for (int floor = 0; floor < parkingSpace.length; floor++) {
            for (int area = 0; area < parkingSpace[floor].length; area++) {
                for (int slot = 0; slot < parkingSpace[floor][area].length; slot++) {
                    Car car = parkingSpace[floor][area][slot];
                    if (car != null) {
                        List<String> carNumbers = carsByFloor.computeIfAbsent(floor, k -> new ArrayList<>());
                        carNumbers.add(car.getCarNumber());
                    }
                }
            }
        }

        // Print grouped cars by floor
        System.out.println("Parked Cars by Floor:");
        System.out.println("+-------+-------------------------------------+-------------+");
        System.out.println("| Floor | Car Numbers                         | Enter Time  |");
        System.out.println("+-------+-------------------------------------+-------------+");

        carsByFloor.forEach((floor, carNumbers) -> {
            // Join car numbers into a single string
            String carNumbersStr = String.join(", ", carNumbers);

            // Assuming enter time is the same for simplification, adjust as needed
            String enterTimes = carNumbers.stream()
                    .map(carNumber -> findCar(carNumber).getEnterTime() + "") // Convert enter times to string
                    .distinct() // Remove duplicates, if any
                    .collect(Collectors.joining(", ")); // Join enter times

            System.out.printf("| %-5d | %-35s | %-11s |%n",
                    floor + 1, // Assuming floors start at 0 internally
                    carNumbersStr,
                    enterTimes.length() > 0 ? enterTimes : "N/A"); // Print enter times if available
        });

        System.out.println("+-------+-------------------------------------+-------------+");
    }
}