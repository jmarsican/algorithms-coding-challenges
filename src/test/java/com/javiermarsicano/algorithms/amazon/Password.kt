package com.javiermarsicano.algorithms.amazon

import org.junit.Assert.assertEquals
import org.junit.Test

/*
The strength of a password is determined by the number of distinct characters within every substring
Example:
password: "test"
t e s t -> 4
te es st -> 6
tes est -> 6
test -> 3

result 19
 */
class Password {

    @Test
    fun `simple example`() {
        val password = "test"

        val strength = findPasswordStrength(password)

        assertEquals(4 + 6 + 6 + 3, strength)
    }

    @Test
    fun `tivial case`() {
        val password = "t"

        val strength = findPasswordStrength(password)

        assertEquals(1, strength)
    }


    @Test
    fun `edge case`() {
        val password = "111"

        val strength = findPasswordStrength(password)

        assertEquals(3 + 2 + 1, strength)
    }

    @Test
    fun `complex case`() {
        val password = "password"

        val strength = findPasswordStrength(password)

        assertEquals(8 + 13 + 16 + 17 + 17 + 15 + 12 + 7, strength)
    }

    fun findPasswordStrength(password: String): Long {
        // Write your code here
        var sum = 0L
        for (subSize in 1..password.length) {
            val characters = mutableSetOf<Char>()
            for (index in 0 until password.length - subSize + 1) {
                characters.add(password[index + subSize - 1])
                sum += characters.size.toLong()
            }
        }
        return sum
    }

}