package pl.sdacademy.repository;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.getAll());
        System.out.println(personRepository.get(8));
        Person malinowska = new Person("Katarzyna", "Malinowska", 55);
        personRepository.add(malinowska);
    }
}
