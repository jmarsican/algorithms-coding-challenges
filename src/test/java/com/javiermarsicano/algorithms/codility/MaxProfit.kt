package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

/**
An array A consisting of N integers is given. It contains daily prices of a stock share for a period
of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N,
then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the
transaction brings loss of A[P] − A[Q].

For example, consider the following array A consisting of six elements such that:
A[0] = 23171   A[1] = 21011  A[2] = 21123  A[3] = 21366  A[4] = 21013  A[5] = 21367
If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048.
If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354.
Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.

Write a function,

fun solution(A: IntArray): Int

that, given an array A consisting of N integers containing daily prices of a stock share for a period
of N consecutive days, returns the maximum possible profit from one transaction during this period.
The function should return 0 if it was impossible to gain any profit.

Write an efficient algorithm for the following assumptions:
N is an integer within the range [0..400,000];
each element of array A is an integer within the range [0..200,000].
 */

class MaxProfitTest {
    @Test
    fun `test simple case`() {
        val array = listOf(23171,21011,21123,21366,21013,21367)

        val result = find(array.toIntArray())

        assertEquals(356, result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(11,12,3,6,5,9,7,2,4)

        val result = find(array.toIntArray())

        assertEquals(6, result)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(2,5)

        val result = find(array.toIntArray())

        assertEquals(3, result)
    }

    @Test
    fun `test edge case`() {
        val array = listOf(0)

        val result = find(array.toIntArray())

        assertEquals(0, result)
    }

    fun find(A: IntArray): Int {
        if (A.size < 2) {
            return 0
        } else {
            var maxProfit = 0

            var max = 0
            var min = A[0]

            var maxIndex = 0
            var minIndex = 0

            for (i in 1 until  A.size) {
                if (A[i] > max) {
                    max = A[i]
                    maxIndex = i
                }
                if (A[i] < min) {
                    min = A[i]
                    minIndex = i
                }
                if (minIndex < maxIndex) {
                    val profit = max - min
                    if (profit > maxProfit) {
                        maxProfit = profit
                        max = 0
                    }
                }
            }

            return maxProfit
        }
    }

}

/**
 Correctness: 100 %
 Performance : 50 %
 Total score: 77 %
 */


