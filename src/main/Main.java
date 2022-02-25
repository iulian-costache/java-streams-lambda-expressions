package main;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Instrument instrument = new Instrument() {
            @Override
            public void play() {
                System.out.println("Starting to play");
            }
        };
        instrument.play();

        Instrument instrument1 = () -> System.out.println("Violin");
        instrument1.play();
        System.out.println("---------------------------------");

        Math sum = (a, b) -> a + b;
        int operation = sum.operation(5, 6);
        System.out.println(operation);
        Math multi1 = (int a, int b) -> a * b;
        Math multi2 = (var a, var b) -> a * b;
        Math divide = (var a, var b) -> {
            return a * b;
        };

        System.out.println(divide.operation(18, 9));

        Function<Integer, Integer> multi3 = (x) -> x * 5;
        BiFunction<String, String, Integer> ex1 = (s1, s2) -> s1.length() + s2.length();
        System.out.println(ex1.apply("Apple", "Car"));

        Reverse reverseAString = (String s) -> new StringBuilder(s).reverse().toString();
        System.out.println(reverseAString.reverse("Finish"));

        List<Person> personList = List.of(
                new Person("George", 15),
                new Person("Dragos", 19),
                new Person("Dan", 24),
                new Person("Alina", 23),
                new Person("Ana", 20)
        );

        System.out.println("Ascending sorting persons by age");
        Comparator<Person> personComparator = (p1, p2) -> p1.getAge() - p2.getAge();
        List<Person> collect = personList.stream()
                .sorted(personComparator)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        List<Integer> list = List.of(10, 2, 15, 4, 50, 6, 7);
        System.out.println("Sorted list of integers");
        list.stream()  // 10,2,15,4,50,6,7
                .sorted() // [2,4,6,7,10,15,50]
                .collect(Collectors.toList())
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("Filter even numbers and sorting from a list");
        List<Integer> collect1 = list.stream() //[10, 2, 15, 4, 50, 6, 7]
                .filter(n -> n % 2 == 0) // [10,2,4,50,6]
                .sorted() //[2,4,6,10,50]
                .collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("Filter persons with age over 18 from a list of persons");
        List<Integer> collect2 = personList.stream()
                .map(person -> person.getAge())
                .filter(x -> x > 18)
                .collect(Collectors.toList());
        System.out.println(collect2);

        System.out.println("Find any match of a name in the list of persons");
        boolean anyMatch = personList.stream()
                .anyMatch(person -> person.getName().equals("Dan"));
        System.out.println("AnyMatch for name Dan ? " + anyMatch);

        Integer reduce = list.stream()  //[1,2,3]
                .reduce(0, (i, j) -> i + j);// 0 + 1=1, 1 + 2= 3, 3 +3 =6

        System.out.println("Creating an infinite stream and limiting to a finite length of 15 elements");
        Stream<Integer> integerStream = Stream.iterate(1, i -> i + 1); // 1,2,3,4 .. //infinite stream
//      integerStream.forEach(System.out::println);
        integerStream.limit(15).forEach(System.out::println);

        List<Integer> integerList = List.of(7, 16, 19, 21, 23, 28);

        System.out.println("Prime numbers from a list v1.0");
        Predicate<Integer> isPrime = (a) -> {
            for (int i = 2; i < a / 2; i++) {
                if (a % i == 0) {
                    return false;
                }
            }
            return true;
        };
        integerList.stream()
                .filter(isPrime)
                .forEach(System.out::println);

        System.out.println("Prime numbers from a list v2.0");
        integerList.stream()
                .filter((n) -> isPrimeStream(n))
                .forEach(System.out::println);

    }

    static boolean isPrimeStream(int n) {
        return IntStream.range(2, n) // note  division by zero possible in your attempt
                .noneMatch(i -> n % i == 0);
    }
}
