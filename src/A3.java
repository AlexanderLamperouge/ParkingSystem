
/**
* Main class for managing a parking lot system.
* 
* Group members:    
                    Huaizhi Ling,
                    QINGYUAN JING,
                    Sansi Yue,
                    YI SHI,
                    Jinyang Liu.
* Date:             
                    5/Feb/2024
*/

import java.util.*;
//import java.time.*;

public class A3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot();

        while (true) {
            System.out.println("\n\n\nPitions:");
            System.out.println("0. Display car‘s list");
            System.out.println("1. Save car");
            System.out.println("2. Leave car");
            System.out.println("3. Print price and time");
            System.out.println("4. Find car");
            System.out.println("5. Help");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice:");

            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    parkingLot.displayCar();
                    break;
                case 1:
                    System.out.println("That is the free parking space: ");
                    parkingLot.displayFreeSpace();
                    // Need a method to get free space

                    System.out.println("Enter your car number: ");
                    String carNumber = sc.next();

                    // Enter the location of car
                    System.out.println("Enter your favourite space: ");
                    System.out.println("Enter the floor(1~5):");
                    int enteredFloor = sc.nextInt();
                    System.out.println("Enter the Area(A,B,C,D)");
                    String enteredArea = sc.next();
                    System.out.println("Enter the Number(0~19):");
                    int enteredNumber = sc.nextInt();

                    int enterTime = Tools.getTime();

                    // Package myCar
                    Car myCar = new Car(carNumber, enterTime,
                            Tools.getLocation(enteredFloor - 1, enteredArea, enteredNumber));

                    parkingLot.enter(myCar);
                    break;
                case 2:
                    System.out.println("Enter your car number: ");
                    String leaveNumber = sc.next();

                    Car leaveOfCar = parkingLot.findCar(leaveNumber);

                    if (leaveOfCar != null) {
                        int exitTime = Tools.getTime();
                        System.out.println("Exit Time: " + exitTime);
                        System.out.println("Total Time: " + (exitTime - leaveOfCar.getEnterTime()));

                        System.out.println("Your price is($100 per hour): ");
                        System.out.println(Tools.countPrice(exitTime, leaveOfCar.getEnterTime()));

                        System.out.println("Leaving your car");
                        parkingLot.leave(leaveNumber);
                    } else {
                        System.out.println("Don't have this car!!!");
                    }
                    break;
                case 3:
                    System.out.println("Enter your car number: ");

                    String numberOfPrice = sc.next();
                    Car priceOfCar = parkingLot.findCar(numberOfPrice);
                    if (priceOfCar != null) {
                        int nowTime = Tools.getTime();

                        System.out.println("Now Time: " + nowTime);
                        System.out.println("Total Time: " + (nowTime - priceOfCar.getEnterTime()) + " Minutes.");
                        System.out.println("Your price is($100 per hour): ");

                        System.out.println(Tools.countPrice(nowTime, priceOfCar.getEnterTime()) + " Dollars.");
                    } else {
                        System.out.println("Don't have this car!!!");
                    }

                    break;
                case 4:
                    System.out.println("Enter your car number: ");
                    String locationOfYourCar = sc.next();
                    System.out.println("Your car is on: ");
                    parkingLot.getLocation(locationOfYourCar);
                    break;
                case 5:
                    System.out.println("Help");
                    Tools.getHelp();
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            if (choice == 6) {
                System.out.println("Goodbye");
                break;
            }
        }

        sc.close();
    }
}
