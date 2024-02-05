public class Location {
    private final String Floor;
    private final String Area;
    private final int Number;

    // 初始化车辆位置信息
    public Location(String enterFloor, String enterArea, int enterNumber) {
        this.Floor = enterFloor;
        this.Area = enterArea;
        this.Number = enterNumber;
    }

    // 获得车辆位置信息
    public Location getLocation() {

        System.out.println("The car's location is: xyz.");
        return this;
    }
}
