package ru.job4j.serialization.json;

import java.util.Arrays;

public class MovieHero {

    private final String name;

    private final boolean isMan;
    private final int age;
    private final Car car;
    private final String[] skills;

    public MovieHero(String name, boolean isMan, int age, Car car, String[] skills) {
        this.name = name;
        this.isMan = isMan;
        this.age = age;
        this.car = car;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Movie Hero {"
                + "isMan=" + isMan
                + ", age=" + age
                + ", car=" + car
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}
