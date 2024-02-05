
public class ParkingLot {
    private ParkingSpace parkingSpace;

    // Create instance 实例化类
    public ParkingLot() {
        this.parkingSpace = new ParkingSpace();
    }

    /*
     * This method can save the car enter.
     * 
     * @input class of car.
     * 
     * @return if car enterable, return true. If the car can't enter, return false.
     */

    // 存车
    public boolean enter(Car enterCar) {
        this.parkingSpace.saveCar(enterCar);

        return true;
    }

    // 取车
    public boolean leave(String licensePlate) {
        return true;
    }

    // 计算停车费和停车时长
    public void getPrice(String carNumber) {
        System.out.println("Price and time.");
    }

    // 找到车位
    public void getSpace(String carNumber) {
        System.out.println("Your car's space.");
    }
}
