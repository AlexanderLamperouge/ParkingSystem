/**
 * Represents a parking spot's location within a parking lot.
 */
public class Location {
    // Define immutable properties for a location
    private final String Floor; // Floor within the parking lot
    private final String Area; // Specific area/section on the floor
    private final int Number; // Spot number within the area

    /**
     * Constructs a Location object with specified floor, area, and number.
     *
     * @param enterFloor  The floor on which the parking spot is located.
     * @param enterArea   The area or section within the floor.
     * @param enterNumber The specific spot number in the area.
     */
    public Location(String enterFloor, String enterArea, int enterNumber) {
        this.Floor = enterFloor;
        this.Area = enterArea;
        this.Number = enterNumber;
    }

    /**
     * Retrieves the location of the parking spot.
     * Note: This method currently prints a placeholder message and returns the
     * Location object itself.
     * Consider implementing functionality to dynamically generate the location
     * description based on the object's fields.
     *
     * @return The current Location object.
     */
    public Location getLocation() {
        // Placeholder implementation; consider enhancing to use Floor, Area, and Number
        // for a meaningful output.
        System.out.println("The car's location is: xyz."); // Example message; replace 'xyz' with actual location
                                                           // details.
        return this;
    }
}
