class seatwork2part1 {
    protected String brand = "FERARI";
    public void honk() {
        System.out.println("VROOOOOOOOM!");
    }
}

class Car extends seatwork2part1 {
    private String modelName = "458 ITALIA";
    public static void main(String[] args) {
        Car myFastCar = new Car();
        myFastCar.honk();
        System.out.println(myFastCar.brand + " " + myFastCar.modelName);
    }
}
