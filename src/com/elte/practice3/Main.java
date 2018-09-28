package com.elte.practice3;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> collectedList = Stream.of(344, 3232, 4545, 3232).collect(Collectors.toList());
        Set<Integer> collectedSet = Stream.of(344, 3232, 4545, 3232).collect(Collectors.toSet());

        // Map
        Map<Integer, String> collectedMap = Stream.of(43324, 344, 4, 53, 2)
                .collect(Collectors.toMap(n -> n * 2, n -> "value: " + n));

        // with lambdas
        Function<Integer, Map.Entry<Integer, String>> toEntry = n -> new AbstractMap.SimpleEntry<>(n * 2, "value: " + n);

        System.out.println(collectedMap);
        System.out.println(toEntry.apply(33454));

        Map<Integer, Set<String>> mapWithDuplicates = Stream.of(23, 24, 25, 26)
                .collect(Collectors.toMap(
                   n -> n % 2,
                   n -> {
                       Set<String> retVal = new HashSet<String>();
                       retVal.add("value: " + n);
                       return retVal;
                   },
                   (set1, set2) -> {
                        Set<String> retVal = new HashSet<>(set1);
                        retVal.addAll(set2);
                        return retVal;
                   },
                   () -> new HashMap<>() // we should start with an empty map!
                ));

        System.out.println(mapWithDuplicates);

        Integer[] array = Stream.of(12, 12, 1, 3, 4, 5)
                .toArray(Integer[]::new);

        System.out.println(Arrays.toString(array));

        exercise1(args);
        exercise2(args);
        exercise3(15);
    }

    private static IntStream exercise3(int howManyPrimesToShow) {
        // TODO
        return null;
    }

    private static boolean isPrime(int n) {
        if (n <= 1 ) return false;
        else if (n == 2) return true;
        else {
            for (int i = n - 1; i >= 2; i--) {
                if(n % i == 0) return false;
            }
            return true;
        }
    }

    /**
     * Print the sum of even numbers that are greater than 8 from the command line arguments.
     * Ignore all texts that are not numbers.
     * @param args
     */
    private static void exercise2(String[] args) {
        String concat = Stream.of(args).reduce("", (str, prevStr) -> prevStr.concat(str));
        System.out.println(concat);

        int sum = Stream.of(args)
                .filter(arg -> arg.matches("[0-9]+"))
                .mapToInt(Integer::parseInt)
                .filter(arg -> arg > 8)
                .sum();

//                .reduce(
//                    0,
//                    (prevSum, str) -> str + prevSum,
//                    (num1, num2) -> num1 + num2);
        System.out.println(sum);
    }

    /**
     * TODO Finish
     * Print the lengths of the command line arguments in reverse order.
     * @param args
     */
    private static void exercise1(String[] args) {
        List<String> stream = Stream.of(args).collect(Collectors.toList());
        BiFunction <List<String>, Integer, List<String>> f = (xs, i) -> {
            if(i >= xs.size()) return xs;
            else {
                i++;
                xs.add(xs.get(xs.size() - i));
                return xs;
            }
        };
        System.out.println(f.apply(stream, 0));
    }



}
