package by.kihtenko.model;

import java.util.List;

public class Owner {
    private int id;
    private Person person;
    private List<Animal> animals;

    public Owner() {
    }

    public Owner(int id, Person person, List<Animal> animals) {
        this.id = id;
        this.person = person;
        this.animals = animals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
