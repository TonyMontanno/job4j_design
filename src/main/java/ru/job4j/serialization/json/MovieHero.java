package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Arrays;

@XmlRootElement(name = "movieHero")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieHero {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean isMan;

    @XmlAttribute
    private int age;

    private Car car;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public MovieHero() {
    }

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