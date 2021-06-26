package pl.sdacademy.repository;

public class Person {
    private Integer id; // ktora osoba jest ktora osobą
    private String firstName;
    private String lastname;
    private int age;



    public Person(int id, String firstName, String lastname, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
    }
    //5. Dodaj do typu Person konstruktor nieprzyjmujący id jako parametr (niech przydzielaniem id zajmie się obiekt typu PersonRepository).
// Zmień typ pola id na Integer - niezapisana osoba będzie miała wartość null przypisaną do tego pola.
    public Person(String firstName, String lastname, int age) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }
}
