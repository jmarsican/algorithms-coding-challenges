package com.javiermarsicano.algorithms.codility

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
Your server has received a package of N incoming requests. Handling the K-th request (for K from 0 to N - 1) will take A[K]
seconds.

The load balancer has to drop two acquired requests and distribute the rest of them between three workers in such a way that
each worker receives a contiguous fragment of requests to handle, and finishes handling them in exactly the same moment as
the other workers. No two workers should receive the same request to compute.

The load balancers distribution of requests is performed by selecting two requests that will be dropped, and which will split
the array into three contiguous parts that will be allocated to the workers. More precisely, if requests 2 and 5 are chosen
by the load balancer from a package of 9 requests, then:
• the 0-th to 1-st requests will be handled by the first worker,
• the 3-rd to 4-th requests will be handled by the second worker,
• the 6-th to f-th requests will be handled by the third worker.

Such a distribution will be correct if each worker receives requests equalling the same total amount of handling time.

Write a function solution that given an array A of N integers representing the time of execution of consecutive tasks,
returns true if it is possible for the load balancer to choose two requests that will determine an even distribution of
requests among three workers, or false otherwise.

Examples:
1. Given A = [1, 3, 4, 2,2,2,1,1, 2], the function should retum true, as choosing requests 2 and 5 results in a distribution
giving 4 seconds of handling time to each worker, as explained in the
2. Given A=[1,1,1,1,1,1] the function should return false.
3. Given A= [1, 2,1, 2,...,1, 2] of length 20,000, the function should return true.
Write an efficient algorithm for the following assumptions:
• each element of array A is an integer within the range [1..10,000].
• minimum size of array is 5.
 */

class SolutionTest {
    @Test
    fun `test simple case`() {
        val array = listOf(1, 1, 1, 1, 1, 1)

        val result = solution(array.toIntArray())

        assertFalse(result)
    }

    @Test
    fun `test simple case5`() {
        val array = listOf(1, 3, 4, 2, 2, 2, 1, 1, 2)

        val result = solution(array.toIntArray())

        assertTrue(result)
    }

    @Test
    fun `test simple case2`() {
        val array = listOf(1,2,1,15,1)

        val result = solution(array.toIntArray())

        assertTrue(result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(5,0,9,2,2,1,9,1,1,2,1)

        val result = solution(array.toIntArray())

        assertTrue(result)
    }

    @Test
    fun `test complex case2`() {
        val array = listOf(3,99,1,1,1,99,3)

        val result = solution(array.toIntArray())

        assertTrue(result)
    }

    @Test
    fun `test complex case3`() {
        val array = listOf(99, 1, 1, 1, 1)

        val result = solution(array.toIntArray())

        assertFalse(result)
    }

    @Test
    fun `test complex case4`() {
        val array = listOf(9, 1, 9, 1, 4, 5)

        val result = solution(array.toIntArray())

        assertTrue(result)
    }

    /**
     * Optimized
     * */
    fun solution(A: IntArray): Boolean {
        val headEnd = A.size - 3

        var aAcum = 0
        var dAcum = 0

        var d = A.size - 1
        for (a in 0 until headEnd) {
            aAcum += A[a]
            while (d > a + 2 && dAcum < aAcum) {
                dAcum += A[d]
                d--
            }
            if (dAcum == aAcum) {
                if (validateMiddle(aAcum, A, a+1, d)) {
                    return true
                }
            }
        }

        return false
    }

}


/**
Expected time: 30 mins
My time: 90 mins
Computational complexity performance: 40%
 **/

object LoadBalancer {
    fun solution(A: IntArray): Boolean {
        val headEnd = A.size - 4
        val tailStart = A.size - 2

        var headAcum = A[0]
        var tailAcum = A[A.size-1] //last element
        for (head in 1..headEnd) {
            for (tail in tailStart downTo 3) {
                if (headAcum == tailAcum) {
                    if (validateMiddle(headAcum, A, head, tail)) {
                        return true
                    }
                } else if (headAcum < tailAcum) {
                    continue
                }
                tailAcum += A[tail]

            }
            tailAcum = A[A.size-1] //restart acum with last element value of array
            headAcum += A[head]
        }
        return false
    }


}

fun validateMiddle(headAcum: Int, A: IntArray, head: Int, tail: Int): Boolean {
    var acum = 0
    for (i in head + 1 until tail) {
        acum += A[i]
        if (acum > headAcum) {
            return false
        }
    }
    return acum == headAcum
}




