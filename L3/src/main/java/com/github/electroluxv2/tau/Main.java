package com.github.electroluxv2.tau;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final var toSort = new int[] { 5, 1, 89, 255, 7, 88, 200, 123, 66 };

        Arrays.sort(toSort);

        System.out.printf("Sorted: %s%n", Arrays.toString(toSort));
    }
}