package com.javiermarsicano.algorithms.codility

import org.junit.Assert.assertEquals
import org.junit.Test

class EquilibriumTest {
    @Test
    fun `test trivial case`(){
        val array = listOf(3,0,3)

        val result = Equilibrium.find(array)

        assertEquals(1, result)
    }

    @Test
    fun `test edge case`(){
        val array = listOf(0,8,777,1,1,1,1,2,2)

        val result = Equilibrium.find(array)

        assertEquals(2, result)
    }

    @Test
    fun `test simple equilibrium case`(){
        val array = listOf(2,3,-1,4,6,5,3,0)

        val result = Equilibrium.find(array)

        assertEquals(4, result)
    }

    @Test
    fun `test all ones`(){
        val array = listOf(1,1,1,1,1,1,1,1,1)

        val result = Equilibrium.find(array)

        assertEquals(4, result)
    }

    @Test
    fun `test no equilibrium`(){
        val array = listOf(0,0,1,2)

        val result = Equilibrium.find(array)

        assertEquals(-1, result)
    }

    @Test
    fun `test all zeros`() {
        val array = listOf(0,0)

        val result = Equilibrium.find(array)

        assertEquals(-1, result)
    }
}

object Equilibrium {
    fun find(array: List<Int>): Int {
        val acumulations = buildAcumulationsMemo(array)

        val lastPosition = array.size - 1
        var descAcum = array[lastPosition]

        for (p in lastPosition - 1 downTo 1) {
            if (descAcum == acumulations[p - 1])
                return p
            descAcum += array[p]
        }

        return -1
    }

    private fun buildAcumulationsMemo(array: List<Int>): List<Int> {
        val acumulations = ArrayList<Int>(array.size)
        var acum = 0

        for (value in array) {
            acum += value
            acumulations.add(acum)
        }
        return acumulations
    }

}