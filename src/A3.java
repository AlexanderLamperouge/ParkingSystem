
/**
 * Main class for managing a parking lot system.
 * 
 * Group members: Huaizhi Ling(A1), Jinyang Liu(A3code), QINGYUAN JING(A2), Sansi Yue(A3Flowchart and output), YI SHI(A1)
 * Date: 5/Feb/2024
 */

import java.util.*;
//import java.time.*;

public class A3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot();

        while (true) {
            System.out.println("Pitions:");
            System.out.println("1. Save car");
            System.out.println("2. Leave car");
            System.out.println("3. Print price and time");
            System.out.println("4. Find car");
            System.out.println("5. Help");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice:");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("That is the free parking space: ");
                    // Need a method to get space

                    System.out.println("Enter your car number: ");
                    String carNumber = sc.next();

                    System.out.println("Enter your favourite space: ");
                    // TODO: Need to enter the location of car 需要一个输入车辆位置信息的函数
                    Location carSpace = Tools.getLocation(myfloor, myarea, mynumber);
                    // TODO: Need a method to save the favourite space 需要一个格式化保存车辆位置信息的函数

                    // Create instance 创建Car实例
                    parkingLot.enter(new Car(carNumber, Tools.getTime(), carSpace));
                    break;
                case 2:
                    System.out.println("Enter your car number: ");
                    String leaveNumber = sc.next();
                    System.out.println("Leaving your car");
                    parkingLot.leave(leaveNumber);
                    break;
                case 3:
                    System.out.println("Enter your car number: ");
                    String numberOfPrice = sc.next();
                    System.out.println("Your price and time is: ");
                    parkingLot.getPrice(numberOfPrice);
                    break;
                case 4:
                    System.out.println("Enter your car number: ");
                    String numberOfFind = sc.next();
                    System.out.println("Your car is on: ");
                    parkingLot.getSpace(numberOfFind);
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
