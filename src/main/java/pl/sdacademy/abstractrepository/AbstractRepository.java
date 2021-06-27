package pl.sdacademy.abstractrepository;

import pl.sdacademy.repository.Car;
import pl.sdacademy.repository.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> {
    private List<T> entities;
    private Path filePath;

    public AbstractRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            entities = Files.lines(filePath)
                    .map(this::createEntity)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    protected abstract T createEntity(String fileLine);

    public List<T> getAll() {
        return entities;
    }
    public T get(int id) {
        return entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }
    private void updateFile() {
        List<String> fileLines = entities.stream()
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
        for (T entitiy : entities) {
            if (entitiy.getId() > maxId) {
                maxId = entitiy.getId();
            }
        }
        return maxId + 1;
    }
    protected abstract String createFileLine(T entity);

    public void add(T entity){
        entity.setId(generateNextId());
        entities.add(entity);
        updateFile();
    }
//    7. Dodaj metodę delete, która przyjmie jako parametr wartość typu int, a która usunie z repozytorium osobę o zadanym id.
//    public void delate(int id){
//        entities.stream()
//                .filter(entity -> entity.getId() == id)
//                .findFirst()
//
//    }
}
