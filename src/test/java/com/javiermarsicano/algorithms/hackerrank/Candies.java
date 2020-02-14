package com.javiermarsicano.algorithms.hackerrank;



import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 Alice is a kindergarten teacher. She wants to give some candies to the children in her class.  All the children 
 sit in a line (their positions are fixed), and each  of them has a rating score according to his or her performance 
 in the class.  Alice wants to give at least 1 candy to each child. If two children sit next to each other, then the
 one with the higher rating must get more candies. Alice needs to minimize the total number of candies given to the
 children.
 For example, assume her students' ratings are [4, 6, 4, 5, 6, 2]. She gives the students candy in the following
 minimal amounts: [1, 2, 1, 2, 3, 1]. She must buy a minimum of 10 candies.
 */
public class Candies {

    @Test
    public void testSpecialCase() {
	//this case fails because first and last children have to be assigned 1 always
        int[] rates = {8, 2, 4, 3, 5, 2, 6, 4, 5};

        long result = candies(rates.length, rates);

        assertEquals(12, result);
    }

    @Test
    public void testSampleCase() {
        int[] rates = {4, 6, 4, 5, 6, 2};

        long result = candies(rates.length, rates);

        assertEquals(10, result);
    }

    @Test
    public void testSmallCase() {
        int[] rates = {1, 2, 2};

        long result = candies(rates.length, rates);

        assertEquals(4, result);
    }

    @Test
    public void testLargeCase() {
        int[] rates = {2, 4, 2, 6, 1, 7, 8, 9, 2, 1};

        long result = candies(rates.length, rates);

        assertEquals(19, result);
    }

    @Test
    public void testLargeCase2() {
        int[] rates = {2, 4, 3, 5, 2, 6, 4, 5};

        long result = candies(rates.length, rates);

        assertEquals(12, result);
    }

    long candies(int n, int[] arr) {
        long[] assignments = new long[n];
        assignments[0] = 1;

        //left to right
        for (int index = 1; index < n; index++) {
            assignments[index] = (arr[index - 1] < arr[index]) && (assignments[index] < assignments[index - 1]) ?
                    assignments[index - 1] + 1 : 1;
        }

        //right to left
        for (int indexR = n - 2; indexR >= 0; indexR--) {
            if ((arr[indexR + 1] < arr[indexR]) && (assignments[indexR] <= assignments[indexR + 1]))
                assignments[indexR] = assignments[indexR + 1] + 1;
        }

        long result = Arrays.stream(assignments).peek(System.out::print).sum();
        System.out.println();

        return result;
    }
}

