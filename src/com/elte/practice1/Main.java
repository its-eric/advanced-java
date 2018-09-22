package com.elte.practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Main {

    public enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        int current = 0;

        public Weekday getCurrent() {
            Weekday value = Weekday.values()[(current % 7)];
            current++;
            return value;
        }

        public Weekday getNPlus(Weekday weekday, int n) {
            int jump;
            if(n < 0) {
                jump = (7 + (n % 7)) % 7;
            } else {
                jump = n % 7;
            }
            return Weekday.values()[this.ordinal() + jump];
        }
    }

    /**
     * Make a lambda that, when called many times over, returns the days of the week in order.
     * If called even more times, it starts at the first day again.
     */
    private static void exercise1() {
        Weekday week = Weekday.MONDAY;

        Supplier<Weekday> daysOfTheWeek = week::getCurrent;

        System.out.println("Monday should be equal to Monday: " + ((daysOfTheWeek.get().equals(Weekday.MONDAY)) ? "pass" : "fail"));
        System.out.println("Tuesday should be equal to Tuesday: " + ((daysOfTheWeek.get().equals(Weekday.TUESDAY)) ? "pass" : "fail"));
        daysOfTheWeek.get();
        daysOfTheWeek.get();
        daysOfTheWeek.get();
        daysOfTheWeek.get();
        daysOfTheWeek.get();
        System.out.println("Should be Monday again: " + ((daysOfTheWeek.get().equals(Weekday.MONDAY)) ? "pass" : "fail"));
    }

    /**
     * Make a lambda that takes a day of the week and a number n, and returns the weekday that is n days after the other one.
     * Keep in mind that n can be a big number, or a negative one.
     */
    private static void exercise2() {
        BiFunction<Weekday, Integer, Weekday> lambda = (Weekday weekday, Integer n) -> weekday.getNPlus(weekday, n);
        System.out.println("Monday plus 2 is Wednesday: " + (lambda.apply(Weekday.MONDAY, 2).equals(Weekday.WEDNESDAY) ? "pass" : "fail"));
        System.out.println("Monday minus 3 is Friday: " + (lambda.apply(Weekday.MONDAY, -3).equals(Weekday.FRIDAY) ? "pass" : "fail"));
        System.out.println("Monday minus 10 is Friday again: " + (lambda.apply(Weekday.MONDAY, -10).equals(Weekday.FRIDAY) ? "pass" : "fail"));
        System.out.println("Monday plus 70 should be Monday again: " + (lambda.apply(Weekday.MONDAY, 70).equals(Weekday.MONDAY) ? "pass" : "fail"));
    }

    /**
     * Make a lambda that takes two weekdays, and returns whether the first one is earlier in the week than the second one.
     */
    private static void exercise3() {
        BiFunction<Weekday, Weekday, Boolean> lambda = (Weekday w1, Weekday w2) -> w1.compareTo(w2) < 0;

        System.out.println("Monday is not earlier than Monday: " + (! lambda.apply(Weekday.MONDAY, Weekday.MONDAY) ? "pass" : "fail"));
        System.out.println("Sunday is not earlier than Monday... for Europeans: " + (! lambda.apply(Weekday.SUNDAY, Weekday.MONDAY) ? "pass" : "fail"));
        System.out.println("Tuesday is earlier than Friday: " + (lambda.apply(Weekday.TUESDAY, Weekday.FRIDAY) ? "pass" : "fail"));
    }

    private static void exercise4(String[] args) {
        Arrays.sort(args, Comparator.comparingInt(a -> a.toLowerCase().split("a").length));
        for(String arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        exercise1();
        exercise2();
        exercise3();
        exercise4(args);
    }

}
