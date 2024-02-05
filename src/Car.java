
public class Car {
    private String CarNumber;
    private int enterTime;
    private Location carSpace;

    public Car(String licensePlate, int enterTime, Location enterSpace) {
        this.CarNumber = licensePlate;
        this.enterTime = enterTime;
        this.carSpace = enterSpace;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public int getTime() {
        return enterTime;
    }

    public Location getSpace() {
        return carSpace;
    }
}
