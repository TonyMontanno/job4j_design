package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieHeroRunner {
    public static void main(String[] args) {

        final MovieHero movieHero = new MovieHero(
                "John Wick",
                true,
                50,
                new Car("Ford Mustang Mach 1"),
                new String[]{"Hand-to-Hand Combat", "Marksmanship", "Tactical Driving"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(movieHero));

        final String movieHeroJson =
                "{"
                        + "\"name\":\"John Wick\","
                        + "\"isMan\":true,"
                        + "\"age\":50,"
                        + "\"car\":"
                        + "{"
                        + "\"model\":\"Ford Mustang Mach 1\""
                        + "},"
                        + "\"skills\":"
                        + "[\"Hand-to-Hand Combat\",\"Marksmanship\",\"Tactical Driving\"]"
                        + "}";

        final MovieHero movieHeroMod = gson.fromJson(movieHeroJson, MovieHero.class);
        System.out.println(movieHeroMod);
    }
}