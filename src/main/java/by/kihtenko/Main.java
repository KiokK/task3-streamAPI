package by.kihtenko;

import by.kihtenko.model.Animal;
import by.kihtenko.model.Car;
import by.kihtenko.model.Flower;
import by.kihtenko.model.House;
import by.kihtenko.model.Person;
import by.kihtenko.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
//        animals.stream()
//            .filter(a -> "Japanese".equals(a.getOrigin()) )
//                .map(a -> {
//                    if ("Female".equals(a.getGender()))
//                            a.setBread(a.getBread().toUpperCase());
//                    return a;
//                })
//                .map(Animal::getBread)
//                .forEach(System.out::println);
        animals.stream()
                .filter(a -> "Japanese".equals(a.getOrigin()) && "Female".equals(a.getGender()))
                .forEach(a -> a.setBread(a.getBread().toUpperCase()));
        animals.stream()
                .filter(a -> "Japanese".equals(a.getOrigin()))
                .map(Animal::getBread)
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
        animals.stream()
                .filter(a -> "Hungarian".equals(a.getOrigin()) && a.getAge() >= 20 && a.getAge() <= 30)
                .findAny()
                .ifPresentOrElse(System.out::println,
                        () -> {
                            System.out.println("No");
                        });
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> !"Female".equals(a.getGender()) && !"Male".equals(a.getGender()))
                .findAny()
                .ifPresentOrElse((a) -> {
                            System.out.println("No");
                        },
                        () -> {
                            System.out.println("Yes");
                        }
                        );
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(a -> "Oceania".equals(a.getOrigin()))
                .findAny()
                .ifPresentOrElse((a) -> {
                            System.out.println("No");
                        },
                        () -> {
                            System.out.println("Yes");
                        }
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
                .map(animal -> animal.getBread().toCharArray())
                .min((x, y) -> x.length - y.length)
                .get()
                .length
        );
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
                .getSum());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
//        Продолжить...
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        //        Продолжить...
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}