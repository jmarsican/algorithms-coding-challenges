package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

/**
Socks are divided into two drawers: clean and dirty socks. Bob has time for only one laundry and his
washing machine can clean at most K socks. He wants to pick socks for laundering in such a way that
after washing he will have a maximal number of clean, same-colored pairs of socks. It is possible that
some socks cannot be paired with any other sock, because Bob may have lost some socks over the years.

Bob has exactly N clean and M dirty socks, which are described in arrays C and D, respectively. The
colors of the socks are represented as integers (equal numbers representing identical colors).

For example, given four clean socks and five dirty socks:
(1,2,1,1)
(1,4,3,2,4)

If Bob's washing machine can clean at most K = 2 socks, then he can take a maximum of three pairs of
clean socks. He can wash one red sock and one green sock, numbered 1 and 2 respectively. Then he will
have two pairs of red socks and one pair of green socks.

Write a function:

fun solution(K: Int, C: IntArray, D: IntArray): Int

that, given an integer K (the number of socks that the washing machine can clean), two arrays C and D
(containing the color representations of N clean and M dirty socks respectively), returns the maximum
number of pairs of socks that Bob can take on the trip.

For example, given K = 2, C = [1, 2, 1, 1] and D = [1, 4, 3, 2, 4], the function should return 3, as
explained above.

Assume that:

K is an integer within the range [0..50];
each element of arrays C, D is an integer within the range [1..50];
C and D are not empty and each of them contains at most 50 elements.
In your solution, focus on correctness. The performance of your solution will not be the focus of the
assessment.
 */

class SocksLaundering {
    @Test
    fun `test simple case`() {
        val array = listOf(1,2,1,1)
        val arrayB = listOf(1,4,3,2,4)

        val result = find(4, array.toIntArray(), arrayB.toIntArray())

        assertEquals(4, result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(1,2,1,1,3)
        val arrayB = listOf(1,4,3,2,4)

        val result = find(5, array.toIntArray(), arrayB.toIntArray())

        assertEquals(5, result)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(1,1)
        val arrayB = listOf(1,1)

        val result = find(2, array.toIntArray(), arrayB.toIntArray())

        assertEquals(2, result)
    }

    @Test
    fun `test small simple case`() {
        val array = listOf(1,1,2)
        val arrayB = listOf(2,2,3)

        val result = find(5, array.toIntArray(), arrayB.toIntArray())

        assertEquals(2, result)
    }

    @Test
    fun `test edge case`() {
        val array = listOf(1,2)
        val arrayB = listOf(3,4)

        val result = find(2, array.toIntArray(), arrayB.toIntArray())

        assertEquals(0, result)
    }

    @Test
    fun `test no laundry case`() {
        val array = listOf(1,2,3,4)
        val arrayB = listOf(3,2,1,5)

        val result = find(0, array.toIntArray(), arrayB.toIntArray())

        assertEquals(0, result)
    }

    @Test
    fun `test no laundry case2`() {
        val array = listOf(1,4,6,6,3,4)
        val arrayB = listOf(3,2,1,5)

        val result = find(0, array.toIntArray(), arrayB.toIntArray())

        assertEquals(2, result)
    }
}

fun find(K: Int, C: IntArray, D: IntArray): Int {
    var count = 0
    var washBasket = 0

    C.sort()
    val dirty = D.toMutableList()

    var i = 0
    while (i < C.size - 1) {
        if (C[i] == C[i + 1]) {
            i += 2
            count++
        } else {
            //search odd couple and fill washBasket
            if(washBasket < K)
            for (d in dirty.indices) {
                if (dirty[d] == C[i]) {
                    washBasket++
                    count++
                    dirty.removeAt(d)
                    break
                }
            }
            i++

        }
    }

    if (i == C.size - 1) {
        for (d in dirty.indices) {
            if (dirty[d] == C[i]) {
                washBasket++
                count++
                dirty.removeAt(d)
                break
            }
        }
    }

    val oddSocksSet = mutableSetOf<Int>()

    if (dirty.size >= 2) {
        oddSocksSet.add(dirty[0])
        var j = 1
        while (j <= dirty.size - 1 && washBasket < K - 1) { //we take two so if only one space remains then stop
            if (oddSocksSet.contains(dirty[j])) {
                oddSocksSet.remove(dirty[j])
                washBasket += 2
                count++
            } else {
                oddSocksSet.add(dirty[j])
            }
            j++
        }
    }

    return count
}


/**
 * maximal possible test cases: wrong answer, got 21 expected 20
 Correctness: 90 %
 Total score: 90 %
 */


