package pl.sdacademy.abstractrepository;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository("cars.txt");
        System.out.println(carRepository.getAll());
        System.out.println(carRepository.get(10));
        Car car = new Car(13, "afsf", "fsaf");
        carRepository.add(car);
    }
}
