package com.elte.practice1;

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
    }

    /**
     * Make a lambda that, when called many times over, returns the days of the week in order.
     * If called even more times, it starts at the first day again.
     */
    private static void exercise1() {
        Weekday week = Weekday.MONDAY;

        Supplier<Weekday> daysOfTheWeek = () ->  {
            return week.getCurrent();
        };

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
     * Make a lambda that takes a day of the week and a number n, and returns the weekday that is n days after the other one. Keep in mind that n can be a big number, or a negative one.
     */
    private static void exercise2() {
        BiFunction<Weekday, Integer, Weekday> l = (Weekday weekday, Integer n) -> {
            for (int i = 0; i < n; i++) {
                System.out.println("To be implemented");
            }
            return null;
        };
    }

    public static void main(String[] args) {
        exercise1();
    }
}
