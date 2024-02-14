import java.time.*;
//import java.util.*;

public class Tools {

    // This method can get the time of car enter
    //
    public static int getTime() {
        int enterTime = LocalTime.now().getHour() * 100 + LocalTime.now().getMinute();
        return enterTime;
    }

    // This method calculates the price based on enter time
    public static int countPrice(int exitTime, int enterTime) {
        // Convert enterTime to LocalTime for easier manipulation
        int enterHour = enterTime / 100;
        int enterMinute = enterTime % 100;

        // Get the current time
        int nowHour = exitTime / 100;
        int nowMinute = exitTime % 100;

        // Calculate the difference in time
        int hourDifference = nowHour - enterHour;
        int minuteDifference = nowMinute - enterMinute;

        // Adjust for when enter minutes are greater than now minutes
        if (minuteDifference < 0) {
            hourDifference -= 1; // Subtract an hour
            minuteDifference += 60; // Add 60 minutes to the minute difference
        }

        // Convert total parked time to minutes
        int totalMinutesParked = hourDifference * 60 + minuteDifference;

        // Assuming the rate is $10 per hour, calculate the price
        // Convert hours to minutes in the price calculation to handle partial hours
        int yourPrice = totalMinutesParked * 100 / 60; // $100 per hour

        return yourPrice;
    }

    //
    public static void getHelp() {
        System.out.println("Parking Lot System Help Guide:\n");

        System.out.println("Save Car (Option 1):");
        System.out.println("\t• Displays available parking spaces.");
        System.out.println("\t• Enter your car number when prompted.");
        System.out.println(
                "\t• Choose your preferred parking space by specifying the floor (1-5), area (A, B, C, D), and number (0-20).");
        System.out.println("\t• Your car will be saved in the chosen spot.\n");

        System.out.println("Leave Car (Option 2):");
        System.out.println("\t• Enter your car number to initiate the car leaving process.");
        System.out.println("\t• The system calculates the total time parked and the applicable parking fee.");
        System.out.println("\t• Confirms your car's exit from the parking lot.\n");

        System.out.println("Print Price and Time (Option 3):");
        System.out.println("\t• Enter your car number to get the current parking fee and the total time parked.");
        System.out.println("\t• Useful for checking parking charges before actual exit.\n");

        System.out.println("Find Car (Option 4):");
        System.out.println("\t• Enter your car number to locate your vehicle within the parking lot.");
        System.out.println("\t• The system provides the exact location (floor, area, and number).\n");

        System.out.println("Help (Option 5):");
        System.out.println("\t• Displays this help guide.\n");

        System.out.println("Exit (Option 6):");
        System.out.println("\t• Exits the system.\n");
    }

    //
    public static Location getLocation(int enterFloor, String enterArea, int enterNumber) {

        // Initialize the variable
        int Floor = enterFloor;
        String Area = enterArea;
        int Number = enterNumber;

        Location myLocation = new Location(Floor, Area, Number);

        return myLocation;
    }

}
