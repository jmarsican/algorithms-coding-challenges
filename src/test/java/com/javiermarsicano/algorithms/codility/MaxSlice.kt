package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

/**
A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write a function:

fun solution(A: IntArray): Int

that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum −6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).

Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
 */

class MaxSliceTest {
    @Test
    fun `test simple case`() {
        val array = listOf(3,2,-6,4,0)

        val result = find(array.toIntArray())

        assertEquals(5, result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(5,-7,3,5,-2,4,-1)

        val result = find(array.toIntArray())

        assertEquals(10, result)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(-2)

        val result = find(array.toIntArray())

        assertEquals(-2, result)
    }

    @Test
    fun `test edge case`() {
        val array = listOf(0)

        val result = find(array.toIntArray())

        assertEquals(0, result)
    }

    /**
    Total score: 84 %
    Performance : 60 %
     */
    fun find(A: IntArray): Int {
        var maxSoFar = Int.MIN_VALUE // -2147483648 to consider scenario with all negative numbers

        for (i in A.indices) {
            var acum = 0
            for (j in i until A.size) {
                acum += A[j]
                maxSoFar = if (acum > maxSoFar) acum else maxSoFar
            }
        }

        return maxSoFar
    }

}




