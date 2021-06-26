package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarRepository {
    private List<Car> cars;
    private Path filePath;

    public CarRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            cars = Files.lines(filePath)
                    .map(this::createCar)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    private Car createCar(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        int maxSpead = Integer.parseInt(fileLineParts[1]);
        String model = fileLineParts[2];
        String color = fileLineParts[3];
        return new Car(id, maxSpead, model, color);
    }

    public List<Car> getAll() {
        return cars;
    }

    public Car get(int id) {
        Optional<Car> carOptional = cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (carOptional.isPresent()) {
            return carOptional.get();
        } else {
            return null;
        }
    }

    private void updateFile() {
        List<String> fileLines = cars.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych do pliku ", e);
        }
    }

    private Integer generateNextId() {
        int maxId = 0;
        //Przebiegnij w pętli przez wszystkie osoby w zbiorze przypisanym do pola people.
        for (Car car : cars) {
            if (car.getId() > maxId) {
                maxId = car.getId();
            }
        }
        return maxId + 1;
    }

    private String createFileLine(Car car) {
        return car.getId() + "," + car.getMaxSpeed() + "," + car.getModel() + "," + car.getColor();
    }


    public void add(Car car) {
        car.setId(generateNextId());
        cars.add(car);
        updateFile();
    }
}



