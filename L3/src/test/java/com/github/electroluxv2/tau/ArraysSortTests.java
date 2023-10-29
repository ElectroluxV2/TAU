package com.github.electroluxv2.tau;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArraysSortTests {
    @Test
    void shouldSortReturnSameLengthArray() {
        // noinspection MismatchedReadAndWriteOfArray
        final var toSort = new int[] { 0, 1, 2 };

        Arrays.sort(toSort);

        assertEquals(3, toSort.length);
    }

    @Test
    void shouldSortPositiveNumbers() {
        final var toSort = new int[] { 2, 0, 1, 9, 3 };
        final var sorted = new int[] { 0, 1, 2, 3, 9 };

        Arrays.sort(toSort);

        assertArrayEquals(sorted, toSort);
    }

    @Test
    void shouldSortNegativeNumbers() {
        final var toSort = new int[] { -2, 0, -1, -9, -3 };
        final var sorted = new int[] { -9, -3, -2, -1, 0 };

        Arrays.sort(toSort);

        assertArrayEquals(sorted, toSort);
    }

    @Test
    void shouldNotThrowExceptionForPositiveAndNegativeZero() {
        final var toSort = new int[] { 0, -0 };

        assertDoesNotThrow(() -> Arrays.sort(toSort));
    }

    @Test
    void shouldSortBothPositiveAndNegativeNumbers() {
        final var toSort = new int[] { 0, 3, -1 };
        final var sorted = new int[] { -1, 0, 3 };

        Arrays.sort(toSort);

        assertArrayEquals(sorted, toSort);
    }

    @Test
    void shouldSortStrings() {
        final var toSort = new String[] { "b", "a", "ac", "aa" };
        final var sorted = new String[] { "a", "aa", "ac", "b" };

        Arrays.sort(toSort);

        assertArrayEquals(sorted, toSort);
    }

    @Test
    void shouldMaintainArrayReference() {
        final var o1 = new Object();
        final var o2 = new Object();
        final var toSort = new Object[] { o1, o2 };

        // noinspection ComparatorMethodParameterNotUsed
        Arrays.sort(toSort, (a, b) -> 1);

        assertSame(o1, toSort[0]);
    }

    @Test
    void shouldThrowForUnComparableObjects() {
        final var o1 = new Object();
        final var o2 = new Object();
        final var toSort = new Object[] { o1, o2 };

        assertThrows(ClassCastException.class, () -> Arrays.sort(toSort));
    }

    @Test
    void shouldNotThrowForUnComparableObjectsWhenComparatorSupplied() {
        final var o1 = new Object();
        final var o2 = new Object();
        final var toSort = new Object[] { o1, o2 };

        // noinspection ComparatorMethodParameterNotUsed
        assertDoesNotThrow(() -> Arrays.sort(toSort, (a, b) -> 1));
    }

    @Test
    void resultShouldNotBeNull() {
        final var toSort = new int[] { -1, 2, 3 };

        Arrays.sort(toSort);

        assertNotNull(toSort);
    }

}
