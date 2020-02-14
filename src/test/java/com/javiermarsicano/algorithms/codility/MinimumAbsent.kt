package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

/**
Given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example:
Given A = [1, 3, 6, 4, 1, 2], the function should return 5.
Given A = [1, 2, 3], the function should return 4.
Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */

class MinimumAbsentTest {
    @Test
    fun `test simple case`() {
        val array = listOf(0,0,2,4,3,4)

        val result = MinimumAbsent.find(array.toIntArray())

        assertEquals(1, result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(4,1,2,1,0)

        val result = MinimumAbsent.find(array.toIntArray())

        assertEquals(3, result)
    }

   }

object MinimumAbsent {
    fun find(A: IntArray): Int {
        var out = 1
        var changed: Boolean

        do {
            changed = false
            for (element in A) {
                if (element == out) {
                    out += 1
                    changed = true
                }
            }
        } while (changed)

        return out
    }

}

/**
 Expected time: 30 mins
 My time: 28 mins

 */


