package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieHeroRunner {
    public static void main(String[] args) {
        JSONObject jsonCar = new JSONObject("{\"model\":\"Ford Mustang Mach 1\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Hand-to-Hand Combat");
        list.add("Marksmanship");
        list.add("Tactical Driving");
        JSONArray jsonSkills = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final MovieHero movieHero = new MovieHero(
                "John Wick",
                true,
                50,
                new Car("Ford Mustang Mach 1"),
                new String[]{"Hand-to-Hand Combat", "Marksmanship", "Tactical Driving"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", movieHero.getName());
        jsonObject.put("isMan", movieHero.isMan());
        jsonObject.put("age", movieHero.getAge());
        jsonObject.put("car", jsonCar);
        jsonObject.put("skills", jsonSkills);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(movieHero).toString());
    }

}