package pl.sdacademy.abstractrepository;

public class Person implements Entity {
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

    public Integer getId() {
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

    public void setId(Integer id) {
        this.id = id;
    }
}
