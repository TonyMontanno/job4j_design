package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
public class Car {

    @XmlAttribute
    private String model;

    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + '}';
    }
}
