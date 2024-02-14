/**
 * Represents a parking spot's location within a parking lot.
 */
public class Location {
    // Define immutable properties for a location
    private final int Floor; // Floor within the parking lot
    private final String Area; // Specific area/section on the floor
    private final int Slot; // Spot number within the area

    /**
     * Constructs a Location object with specified floor, area, and number.
     *
     * @param enterFloor The floor on which the parking spot is located.
     * @param enterArea  The area or section within the floor.
     * @param enterSlot  The specific spot number in the area.
     */
    public Location(int enterFloor, String enterArea, int enterSlot) {
        this.Floor = enterFloor;
        this.Area = enterArea;
        this.Slot = enterSlot;
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

    public int getFloor() {
        return this.Floor;
    }

    public String getArea() {
        return this.Area;
    }

    public int getAreaNumber() {
        int myArea = 0;

        switch (this.Area) {
            case "A":
                myArea = 0;
                break;
            case "B":
                myArea = 1;
                break;
            case "C":
                myArea = 2;
                break;
            case "D":
                myArea = 3;
            case "E":
                myArea = 4;
            default:
                break;
        }

        return myArea;
    }

    public int getSlot() {
        return this.Slot;
    }
}