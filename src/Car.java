public class Car {
    private String CarNumber;
    private int enterTime;
    private Location carLocation;

    public Car(String licensePlate, int enterTime, Location enterSpace) {
        this.CarNumber = licensePlate;
        this.enterTime = enterTime;
        this.carLocation = enterSpace;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public Location getLocation() {
        return carLocation;
    }

    public void setCarNumber(String CarNumber) {
        this.CarNumber = CarNumber;
    }
}