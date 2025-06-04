package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.job4j.serialization.json.Car;
import ru.job4j.serialization.json.MovieHero;

import java.io.StringReader;
import java.io.StringWriter;

public class MovieHeroRunner {
    public static void main(String[] args) throws Exception {
        MovieHero movieHero = new MovieHero("John Wick", true, 50, new Car("Ford Mustang Mach 1"),
                new String[]{"Hand-to-Hand Combat", "Marksmanship", "Tactical Driving"});

        JAXBContext context = JAXBContext.newInstance(MovieHero.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(movieHero, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            MovieHero result = (MovieHero) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}