package by.kihtenko;

import by.kihtenko.model.Animal;
import by.kihtenko.model.Car;
import by.kihtenko.model.Flower;
import by.kihtenko.model.House;
import by.kihtenko.model.Person;
import by.kihtenko.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }


    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        final AtomicInteger counter = new AtomicInteger();
        animals.stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 7))
                .get(2)
                .forEach(System.out::println);
//                .forEach((integer, animals1) -> {
//                    System.out.println(integer + "= " + animals1);
//                });

    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> "Japanese".equals(a.getOrigin()))
                .peek(a -> a.setBread(a.getBread().toUpperCase()))
                .filter(a -> "Female".equals(a.getGender()))
                .map(Animal::toString)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> a.getAge() > 30)
                .filter(a -> a.getOrigin().startsWith("A"))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(a -> "Female".equals(a.getGender()))
                .count();
        System.out.println(count);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(a -> a.getAge() >= 20 && a.getAge() <= 30)
                .anyMatch(a -> "Hungarian".equals(a.getOrigin())));
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(a -> "Female".equals(a.getGender()) && "Male".equals(a.getGender()))
        );
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .noneMatch(a -> "Oceania".equals(a.getOrigin()))
        );
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max((x, y) -> x.getAge() - y.getAge())
                .get()
        );
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .map(animal -> animal.getBread())
                .map(bread -> bread.toCharArray())
                .min((x, y) -> x.length - y.length)
                .get()
                .length
        );
        //But instead of two? you can have one
        // .map(animal -> animal.getBread().toCharArray())
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getSum());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getAverage());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "Male".equals(person.getGender())
                        && person.getDateOfBirth().plusYears(18).isBefore(LocalDate.now())
                        && person.getDateOfBirth().plusYears(27).isAfter(LocalDate.now())
                )
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        LocalDate nowDate = LocalDate.now();
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry("Hospital".equals(house.getBuildingType()) ? 0 :
                                (Period.between(person.getDateOfBirth(), nowDate).getYears() < 18
                                        || ("Female".equals(person.getGender())
                                            && Period.between(person.getDateOfBirth(), nowDate).getYears() >= 58)
                                        || ("Male".equals(person.getGender())
                                            && Period.between(person.getDateOfBirth(), nowDate).getYears() >= 63) ? 1 : 2), person)))
                .sorted(Map.Entry.comparingByKey())
                .map(personEntry -> personEntry.getValue())
                .limit(500)
                .forEach(System.out::println);

//        houses.stream()
//                .collect(Collectors.toMap(u -> "Hospital".equals(u.getBuildingType()), House::getPersonList,
//                        (u1, u2) -> {
//                            u1.addAll(u2);
//                            return u1;
//                        }))
//                .forEach((k, v) -> System.out.println(k + " " + v));
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        double waterFor5years = 1.39 * 0.001  * 365 * 5;
                System.out.println(flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparing(Flower::getPrice).thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower -> Pattern.compile("[C-S].*").matcher(flower.getCommonName()).matches())
                .filter(flower -> flower.isShadePreferred())
                .peek(flower -> flower.getFlowerVaseMaterial()
                        .stream()
                        .filter(material -> "Glass".equals(material) || "Aluminum".equals(material) || "Steel".equals(material))
                        .collect(Collectors.toList()))
                .map(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * waterFor5years)
                .collect(summingDouble(f -> f)));
        ;
    }
}