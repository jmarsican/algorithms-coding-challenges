package com.javiermarsicano.algorithms.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 given an array A of N integers, returns the largest integer K > 0 such that both values K and −K (the opposite number) exist in array A.
 If there is no such integer, the function should return 0.

 1. Given A = [3, 2, −2, 5, −3], the function should return 3 (both 3 and −3 exist in array A).
 2. Given A = [1, 1, 2, −1, 2, −1], the function should return 1 (both 1 and −1 exist in array A).
 3. Given A = [1, 2, 3, −4], the function should return 0 (there is no such K for which both values K and −K exist in array A).
*/
public class LargestInteger {

    @Test
    public void test_simple_case() {
        int[] names = {1,1,2,-1,2,-1};
        assertEquals(1, Solution.find(names));
    }

    @Test
    public void test_trivial_case() {
        int[] names = {2,-2};
        assertEquals(2, Solution.find(names));
    }

    @Test
    public void test_edge_case() {
        int[] names = {-9,-6,5,4,6,10,3};
        assertEquals(6, Solution.find(names));
    }

    @Test
    public void test_no_largest_case() {
        int[] names = {1, 2, 3, -4};
        assertEquals(0, Solution.find(names));
    }

    @Test
    public void test_basic_case() {
        int[] names = {3,2,-2,5,-3};
        assertEquals(3, Solution.find(names));
    }

    @Test
    public void test_complex_case() {
        int[] names = {1};
        assertEquals(0, Solution.find(names));

    }

    public static class Solution {
        static int find(int[] array) {
            List<Integer> numbers = Arrays.stream(array)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList());

            if (numbers.size() > 0) {
                int indexNeg = 0;
                Integer currentNeg = numbers.get(indexNeg);

                while (currentNeg < 0 && indexNeg < numbers.size()) {
                    currentNeg = numbers.get(indexNeg);

                    int indexPos = numbers.size() - 1;
                    Integer currentPositive = numbers.get(indexPos);

                    while (indexPos > indexNeg && currentPositive > 0) {
                        if (currentPositive == currentNeg * -1)
                            return currentPositive;
                        indexPos--;
                        currentPositive = numbers.get(indexPos);
                    }

                    indexNeg++;
                }
            }

            return 0;
        }
    }
}
