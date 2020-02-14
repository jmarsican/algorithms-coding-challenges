package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.StringBuilder

/**
There is a board with 2 rows and N columns, represented by a matrix M. Rows are numbered from 0 to 1 from top
to bottom and columns are numbered from 0 to N-1 from left to right. Each cell contains either a 0 or a 1.
You know that:
• the sum of integers in the 0-th (upper) row is equal to U,
• the sum of integers in the 1-st (lower) row is equal to L,
• the sum of integers in the K-th column is equal to C[K].

Your job is to recover M based on this information.

Write a function:
fun solution(U: Int, L: Int, C: IntArray): String
That, given two integers U, L and an array C of N integers, as described above, returns a string describing
the matrix M in the following format. The first part of the string should be the description of the upper
row (N characters: 0 or 1), then there should be comma (,), and finally there should be the description of
the lower row (N characters: 0 or 1.) The output string should not contain any whitespace.

If there exist multiple valid Ms, your function may return any one of them. If no valid M exists, your
function should return the word IMPOSSIBLE.

Examples:
1. Given U = 3, L = 2, C =[2,1,1,0,1] your function may, for example, return 11001,10100
2. Given U = 2, L = 3, C = [0,0,1,1,2] your function should return the word IMPOSSIBLE, because no matrix M
satisfies such conditions.
3. Given U = 2, L = 2, C = [2,0,2,0] your function should return 1010,1010

 */

class RecoverMatrixTest {
    @Test
    fun `test simple case`() {
        val array = listOf(2, 1, 1, 0, 1)
        val upper = 3
        val lower = 2

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    @Test
    fun `test large array case`() {
        val array = listOf(1,1,1,1,1,1,1)
        val upper = 3
        val lower = 4

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    @Test
    fun `test trivial case`() {
        val array = listOf(0)
        val lower = 0
        val upper = 0

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    @Test
    fun `test trivial case2`() {
        val array = listOf(1)
        val lower = 1
        val upper = 0

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    @Test
    fun `test all zeros case`() {
        val array = listOf(0, 0, 0, 0, 0)
        val lower = 0
        val upper = 0

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    @Test
    fun `test simple case2`() {
        val array = listOf(0, 0, 1, 1, 2)
        val lower = 3
        val upper = 2

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        assertEquals("IMPOSSIBLE", result)
    }

    @Test
    fun `test with high values`() {
        val array = listOf(0, 0, 1, 1, 2)
        val lower = 43
        val upper = 62

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        assertEquals("IMPOSSIBLE", result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(2, 0, 2, 0)
        val upper = 2
        val lower = 2

        val result = RecoverMatrix.find(upper, lower, array.toIntArray())

        val resultArrays = result.split(",")
        val upperArray = decodeArray(resultArrays[0])
        val lowerArray = decodeArray(resultArrays[1])

        assertEquals(upper, upperArray.sum())
        assertEquals(lower, lowerArray.sum())
        assertTrue(validateMatrix(upperArray,lowerArray, array.toIntArray()))
        println(resultArrays)
    }

    private fun validateMatrix(upper: IntArray, lower: IntArray, diff: IntArray): Boolean {
        for (i in 0 until diff.size) {
            if (upper[i]+lower[i]!=diff[i])
                return false
        }
        return true
    }

    private fun decodeArray(array: String): IntArray {
        val output = ArrayList<Int>(array.length)

        for (i in 0 until array.length) {
            output.add(array.substring(i,i+1).toInt())
        }

        return output.toIntArray()
    }
}


object RecoverMatrix {
    fun find(U: Int, L: Int, C: IntArray): String {
        if (C.sum() != U + L)
            return "IMPOSSIBLE"

        var uSum = U
        var lSum = L

        val upper = IntArray(C.size)
        val lower = IntArray(C.size)
        upper.fill(0)
        lower.fill(0)

        for (i in 0 until C.size) {
            if (C[i] != 0) {
                if (C[i] == 2) {
                    //complete both
                    upper[i] = 1
                    lower[i] = 1
                    uSum--
                    lSum--
                } else {
                    if (uSum != 0) {
                        upper[i] = 1
                        uSum--
                    } else if (lSum != 0) {
                        lower[i] = 1
                        lSum--
                    } else {
                        //there must be no more non-zero values
                        for (tail in i until C.size) {
                            if (C[tail] != 0) {
                                return "IMPOSSIBLE"
                            }
                        }
                        break
                    }
                }
            }
        }

        if (uSum + lSum == 0) {
            val output = StringBuilder()
            upper.forEach { output.append(it) }
            output.append(",")
            lower.forEach { output.append(it) }
            return output.toString()
        }

        return "IMPOSSIBLE"
    }
}


/**
 Expected time: 30 mins
 My time: 1h

 */


