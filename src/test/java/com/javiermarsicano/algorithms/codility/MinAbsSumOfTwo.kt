package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.abs

/**
CATERPILLAR METHOD
Let A be a non-empty array consisting of N integers.

The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.

For example, the following array A:

A[0] =  1  A[1] =  4  A[2] = -3
has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
Write a function:

fun solution(A: IntArray): Int

that, given a non-empty array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.

For example, given the following array A:
A[0] =  1  A[1] =  4  A[2] = -3
the function should return 1, as explained above.

Given array A:
A[0] = -8  A[1] =  4  A[2] =   A[3] =-1  A[4] =  3
the function should return |(−8) + 5| = 3.

Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */

class MinAbsSumOfTwo {
    @Test
    fun `test complex case`() {
        val array = listOf(2000000000/2)

        val result = solutionOptimized(array.toIntArray())

        assertEquals(2000000000, result)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(-8,4,5,-10,3)

        val result = solutionOptimized(array.toIntArray())

        assertEquals(3, result)
    }

    @Test
    fun `test edge case`() {
        val array = listOf(1,4,-3)

        val result = solutionOptimized(array.toIntArray())

        assertEquals(1, result)
    }
}
const val MAX_VALUE = 1_000_000_000 + 1_000_000_000
const val MIN_VALUE = -MAX_VALUE

fun solutionOptimized(A: IntArray): Int { //didn't pass the performance tests neither
    var min  = MAX_VALUE
    var tailMin = MAX_VALUE
    var tailMax = MIN_VALUE

    for (i in A.indices) {
        if ((tailMax >= -min - A[i] && tailMin <= min - A[i]) ||
            //adding exception to definition condition cause first time it won't fullfill constraints
            (tailMax == MIN_VALUE && tailMin == MAX_VALUE || min == MAX_VALUE)) {
            for (j in i until A.size) {
                val abs = abs(A[i] + A[j])
                if (abs < min) {
                    min = abs
                }

                if (A[j] < tailMin && j > i) {
                    tailMin = A[j]
                }
                if (A[j] > tailMax && j > i) {
                    tailMax = A[j]
                }
            }
        }
        tailMin = MAX_VALUE
        tailMax = MIN_VALUE
    }

    return min
}

fun solution(A: IntArray): Int {
    var min = Int.MAX_VALUE
    var tailMin = Int.MAX_VALUE
    var tailMax = Int.MIN_VALUE

    for (i in 0 until A.size) {
        for (j in i until A.size) {
            val abs = abs(A[i] + A[j])
            if (abs < min)
                min = abs
        }
    }
    return min
}


/**
 Correctness: 100 %
 Performance : 28 %
 Total score: 54 %
 */


