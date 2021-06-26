package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepository {
    private List<Person> people;
    private Path filePath;

    public PersonRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            people = Files.lines(filePath)
//                    .map(fileLine -> createPerson(fileLine))
                    .map(this::createPerson)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    private Person createPerson(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(id, firstName, lastName, age);
    }

    public List<Person> getAll() {
        return people;
    }

    //    1. Dodaj metodę get, która przyjmie za parametr wartość typu int, a która zwróci osobę o zadanym identyfikatorze.
//    Jeśli takiej osoby nie będzie zwróć null.
    public Person get(int id) {
        Optional<Person> personOptional = people.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
        if (personOptional.isPresent()) {
            return personOptional.get();
        } else {
            return null;
        }
    }

    //2. Dodaj do klasy PersonRepository prywatną metodę generateNextId, która nie przyjmie żadnego parametru,
    // a która zwróci pierwszą "wolną" wartość identyfikatora osoby.
    //Niech metoda działa następująco - znajdujemy maksymalny identyfikator i dodajemy do niego 1.
    //    private int generateNextId() {
    //        // znajdujemy maksymalny identyfikator w liście osób,
    //        // dodajemy 1 i wartość zwracamy.
    //    }
    private int generateNextId() {
        //Zadeklaruj zmienną o nazwie maxId typu int - przypisz jej wartość -1.
        int maxId = 0;
        //Przebiegnij w pętli przez wszystkie osoby w zbiorze przypisanym do pola people.
        for (Person person : people) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }
        return maxId + 1;
        //Dla każdej osoby (czyli w ciele pętli) sprawdź, czy identyfikator tej osoby jest większy od maxId.
        // Jeśli tak jest, to zaktualizuj wartość tej zmiennej - ustaw jej wartość id właśnie tej osoby.
        //Na koniec ciała metody zwróć wartość zmiennej maxId.
    }

    //3. Dodaj do klasy PersonRepository prywatną metodę createFileLine,
    // która zadziała odwrotnie do metody createPerson. M
    // etoda przyjmuje jako parametr osobę, a zwraca wiersz, który będzie odpowiadać tej osobie.
//    Przykładowo - dla parametru o wartości będącej osobą o id 3, imieniu Aaa,
//    nazwisku Bbb i wieku 5 metoda zwróci "3,Aaa,Bbb,5".
    private String createFileLine(Person person) {
        return person.getId() + "," + person.getFirstName() + "," + person.getLastName() + "," + person.getAge();
    }

    //4. Dodaj do klasy PersonRepository prywatną metodę updateFile, która zapisze aktualny stan listy osób do pliku (przy użyciu metody createFileLine).
    private void updateFile() {
        List<String> fileLines = people.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych do pliku ", e);
        }
    }
//6. Dodaj do klasy PersonRepository metodę add, która przyjmie jako parametr obiekt typu Person, a która doda do repozytorium daną osobę.
    public void add(Person person){
        person.setId(generateNextId());
        people.add(person);
        updateFile();
    }
}

