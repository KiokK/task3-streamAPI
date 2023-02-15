package by.kihtenko;

import by.kihtenko.model.*;
import by.kihtenko.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
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
        task16();
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
        final double COST_KILOGRAM = 7.14 * 0.001;
        final Map<Integer, Predicate<Car>> PREDICATES_GROUP = new LinkedHashMap<>()
        {
            {
                put(1, (c) -> "Jaguar".equals(c.getCarMake()) || "White".equals(c.getColor()));
                put(2, (c) -> c.getMass() < 1500 &&
                            ("BMW".equals(c.getCarMake()) || "Lexus".equals(c.getCarMake())
                                || "Chrysler".equals(c.getCarMake()) || "Toyota".equals(c.getCarMake())));
                put(3, (c) -> ("Black".equals(c.getColor()) && c.getMass() > 4000)
                            || "GMC".equals(c.getCarMake()) || "Dodge".equals(c.getCarMake()));
                put(4, (c) -> c.getReleaseYear() < 1982 || "Civic".equals(c.getCarModel())
                            || "Cherokee".equals(c.getCarModel()));
                put(5, (c) -> !("Yellow".equals(c.getColor()) || "Red".equals(c.getColor())
                                || "Green".equals(c.getColor()) ||  "Blue".equals(c.getColor()))
                                || c.getPrice() > 40000);
                put(6, (c) -> c.getVin() != null && c.getVin().contains("59"));
                put(7, (car) -> true);
            }
        };

        double sum = cars.stream()
                .map(car -> Map.entry(PREDICATES_GROUP.entrySet().stream()
                        .filter(entry -> entry.getValue().test(car))
                        .findFirst()
                        .get().getKey(), car))
                //  stream pairs <NumberOfGroup(1-7), Car>
                .filter(entry -> entry.getKey() < 7)
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())))

                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(carsIngroup -> Map.entry(carsIngroup,
                        COST_KILOGRAM * carsIngroup.stream()
                                .collect(Collectors.summarizingInt(Car::getMass))
                                .getSum()))
                .peek(valueGroupCarCost -> System.out.println(valueGroupCarCost.getValue()))

                .mapToDouble(groupCarCost -> groupCarCost.getKey().stream()
                        .collect(Collectors.summarizingDouble(Car::getPrice))
                        .getSum())
                .sum();
        System.out.printf("%f",sum);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        double waterFor5years = 1.39 * 0.001 * 365 * 5;
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

    private static void task16() throws IOException {
        List<Owner> owners = Util.getOwners();
        LocalDate nowDate = LocalDate.now();

        owners.stream()
                .flatMap(owner -> owner.getAnimals().stream()
                        .map(animal -> Map.entry(animal, owner.getPerson())))
                .filter(animalPersonEntry ->
                        ("Female".equals(animalPersonEntry.getValue().getGender())
                            && !Pattern.compile("[L-Z].*").matcher(animalPersonEntry.getKey().getBread()).matches())
                        || ("Male".equals(animalPersonEntry.getValue().getGender())
                            && !Pattern.compile("[A-K].*").matcher(animalPersonEntry.getKey().getBread()).matches())
                        || animalPersonEntry.getKey().getAge() > 35
                        || Period.between(animalPersonEntry.getValue().getDateOfBirth(), nowDate).getYears() < 18)
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .peek(animalPersonEntry -> {
                    if ("Female".equals(animalPersonEntry.getKey().getGender()))
                        animalPersonEntry.getKey().setGender("Male");
                    if ("Male".equals(animalPersonEntry.getKey().getGender()))
                        animalPersonEntry.getKey().setGender("Female"); })
                .sorted((o1, o2) -> o1.getKey().getId() - o2.getKey().getId())
                .forEachOrdered( e -> System.out.println(e.getKey() + " COUNT= " + e.getValue().stream().count() + "\n"));
    }
}