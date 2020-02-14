package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

/**
An integer M and a non-empty array A consisting of N non-negative integers are given. All integers in array
A are less than or equal to M.
A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the
elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is,
no individual number occurs more than once in the slice.

For example, consider integer M = 6 and array A such that:
A[0] = 3  A[1] = 4  A[2] = 5  A[3] = 5  A[4] = 2
There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).

The goal is to calculate the number of distinct slices.

Write a function:

fun solution(M: Int, A: IntArray): Int

that, given an integer M and a non-empty array A consisting of N integers, returns the number of distinct slices.

If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.

For example, given integer M = 6 and array A such that:
A[0] = 3 A[1] = 4 A[2] = 5 A[3] = 5 A[4] = 2
the function should return 9, as explained above.

Write an efficient algorithms
 */

class CountDistinctSlicesTest {
    @Test
    fun `test simple case`() {
        val array = listOf(1,2,3,4)

        val result = find(array.toIntArray())

        assertEquals(10, result)
    }

    @Test
    fun `test simple case2`() {
        val array = listOf(1,2,3,4,2,9,8)

        val result = find(array.toIntArray())

        assertEquals(25, result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(3,4,5,5,2)

        val result = find(array.toIntArray())

        assertEquals(9, result)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(1,2,3,4,5)

        val result = find(array.toIntArray())

        assertEquals(15, result)
    }

    @Test
    fun `test edge case`() {
        val array = listOf(1,2,3,4,5,6)

        val result = find(array.toIntArray())

        assertEquals(21, result)
    }
}

fun find(A: IntArray): Int {
    var count = 0
    val registry = mutableSetOf<Int>()
    var index = 0
    var acum = 0

    while (index <= A.size - 1) {
        val elem = A[index]
        if (!registry.contains(elem)) {
            count++
            registry.add(elem)
            index++
        } else {
            acum = acum + innerDistinctSlices(count) - innerDistinctSlices(registry.size - registry.indexOf(elem) - 1)
            count = 0
            index = index - registry.size + registry.indexOf(elem) + 1
            registry.clear()
        }
	//if (acum + innerDistinctSlices(count) > 1_000_000_000) return 1_000_000_000 //with this line it'd reach 90% of score
    }
    acum += innerDistinctSlices(count)

    return acum
}

private fun innerDistinctSlices(count: Int) = (count + 1) * count / 2
/**
___
\
/   i  =  (n+1)*n
---       -------
i=1          2

ej: n=6 -> 6+5+3...+1 = 7*6/2 = 21
 
*/

/**
 Correctness: 100 %
 Performance : 60 %
 Total score: 80 %
 */


