package com.elte.practice4;

import com.elte.practice1.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice4Tests {

    Practice4 tester = new Practice4();

    Main.Weekday weekday;

    @Test
    void testSummer() {

        assertEquals(tester.summer(1, 3), 6);
    }
}
