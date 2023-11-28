package ru.croc.winter.nastyasad.pojo;


public class Pet {
    private Integer medCard;
    private String name;
    private Integer age;

    public Pet(Integer medCard, String name, Integer age) {
        this.medCard = medCard;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getMedCard() {
        return medCard;
    }

    @Override
    public String toString() {
        return getMedCard() + " " + getName() + " " + getAge();
    }
}
