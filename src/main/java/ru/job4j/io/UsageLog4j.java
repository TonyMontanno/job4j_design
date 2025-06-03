package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte favoriteNumber = 9;
        short grade = 11;
        int height = 186;
        float weight = 85.4F;
        double salary = 527000.0;
        long carMileage = 268000L;
        char gender = 'M';
        boolean isMarried = true;

        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("User name: {}, favorite number: {}", name, favoriteNumber);
        LOG.debug("User name: {}, grade: {}", name, grade);
        LOG.debug("User name: {}, height: {}", name, height);
        LOG.debug("User name: {}, weight: {}", name, weight);
        LOG.debug("User name: {}, salary: {}", name, salary);
        LOG.debug("User name: {}, carMileage: {}", name, carMileage);
        LOG.debug("User name: {}, gender: {}", name, gender);
        LOG.debug("User name: {}, isMarried: {}", name, isMarried);
    }
}