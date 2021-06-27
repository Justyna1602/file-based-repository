package pl.sdacademy.abstractrepository;

public class CarRepository extends AbstractRepository<Car> {

    public CarRepository(String filename) {
        super(filename);
    }

    @Override
    protected Car createEntity(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        int maxSpeed = Integer.parseInt(fileLineParts[1]);
        String model = fileLineParts[2];
        String color = fileLineParts[3];
        return new Car(id, maxSpeed, model, color);
    }

    @Override
    protected String createFileLine(Car car) {
        return car.getId() + "," + car.getMaxSpeed() + "," + car.getModel() + "," + car.getColor();
    }
}
