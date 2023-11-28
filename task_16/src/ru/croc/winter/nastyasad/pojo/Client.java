package ru.croc.winter.nastyasad.pojo;

import ru.croc.winter.nastyasad.pojo.Pet;

import java.util.List;

public class Client {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<Pet> pets;

    public Client(Integer id, String name, String surname, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return getSurname() + " " + getName() + " " + getPhoneNumber();
    }
}
