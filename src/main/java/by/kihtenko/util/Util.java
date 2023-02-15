package by.kihtenko.util;

import by.kihtenko.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Util {
    public static final String animalsDataFileName = "src\\main\\resources\\animals.json";
    public static final String recruitsDataFileName = "src\\main\\resources\\recruits.json";
    public static final String carsDataFileName = "src\\main\\resources\\cars.json";
    public static final String flowersDataFileName = "src\\main\\resources\\flowers.json";
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Animal> getAnimals() throws IOException {
        return newMapper().readValue(new File(animalsDataFileName), new TypeReference<>() {
        });
    }

    public static List<Person> getPersons() throws IOException {
        return newMapper().readValue(new File(recruitsDataFileName), new TypeReference<>() {
        });
    }

    public static List<Car> getCars() throws IOException {
        return newMapper().readValue(new File(carsDataFileName), new TypeReference<>() {
        });
    }

    public static List<Flower> getFlowers() throws IOException {
        return newMapper().readValue(new File(flowersDataFileName), new TypeReference<>() {
        });
    }

    public static List<House> getHouses() throws IOException {
        List<Person> personList = getPersons();
        return List.of(
            new House(1, "Hospital", personList.subList(0, 40)),
            new House(2, "Civil building", personList.subList(41, 141)),
            new House(3, "Civil building", personList.subList(142, 200)),
            new House(4, "Civil building", personList.subList(201, 299)),
            new House(5, "Civil building", personList.subList(300, 399)),
            new House(6, "Civil building", personList.subList(400, 499)),
            new House(7, "Civil building", personList.subList(500, 599)),
            new House(8, "Civil building", personList.subList(600, 699)),
            new House(9, "Civil building", personList.subList(700, 799)),
            new House(10, "Civil building", personList.subList(800, 899)),
            new House(11, "Civil building", personList.subList(900, 999))
        );
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    public static List<Owner> getOwners() throws IOException {
        List<Person> personList = getPersons();
        List<Animal> animalList = getAnimals();
        return List.of(
                new Owner(1, personList.get(0), animalList.subList(0, 40)),
                new Owner(2, personList.get(1), animalList.subList(41, 141)),
                new Owner(3, personList.get(2), animalList.subList(142, 200)),
                new Owner(4, personList.get(3), animalList.subList(201, 299)),
                new Owner(5, personList.get(4), animalList.subList(300, 399)),
                new Owner(6, personList.get(5), animalList.subList(400, 499)),
                new Owner(7, personList.get(6), animalList.subList(500, 599)),
                new Owner(8, personList.get(7), animalList.subList(600, 699)),
                new Owner(9, personList.get(8), animalList.subList(700, 799)),
                new Owner(10, personList.get(9), animalList.subList(800, 899)),
                new Owner(11, personList.get(10), animalList.subList(900, 999))
        );
    }
}
