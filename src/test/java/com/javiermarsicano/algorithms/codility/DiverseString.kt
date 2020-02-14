package com.javiermarsicano.algorithms.codility

import junit.framework.Assert.*
import org.junit.Test
import java.lang.StringBuilder

/**
We consider alphabet with only three letters: 'a', 'b' and 'c'. A string is called diverse if no three consecutive
letters are the same. In other words, a diverse string may not contain any of the strings "aaa","bbb" or "ccc"
Write a function:
fun solution(A: Int, B: Int, C: Int) String
that, given three integers A, B and C, returns any longest possible diverse string containing at most A letters 'a',
at most B letters 'b' and at most C letters 'c'. If there is no possibility of building any string, return empty string.

Examples:
1. Given A= 6, B = 1 and C = 1, your function may return "aabaacaa". Note that "aacaabaa" would also be a correct answer.
Your function may return any correct answer.
2. Given A= 1, B = 3 and C = 1 your function may return "abbcb","bcbab","bacbb" or any of several other strings.
3. Given A = 0, B = 1 and C = 8 your function should return "ccbcc", which is the only correct answer in this case.

Assume that
• A B and C ere integers within the range [0..100]
• A+B+C > O
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */

class TestDiverse {
    @Test
    fun `test simple case`() {

        val result = solution(0,1,8)

        assertEquals("ccbcc", result)
    }

    @Test
    fun `test trivial case`() {

        val result = solution(6,1,1)

        assertEquals("aacaabaa", result)
    }

    @Test
    fun `test trivial case2`() {

        val result = solution(0,1,0)

        assertEquals("b", result)
    }

    @Test
    fun `test small case`() {

        val result = solution(1,3,1)

        assertEquals("abcbb", result)
    }

}

fun solution(A: Int, B: Int, C: Int): String {
    if (A == 0 && B == 0 && C == 1) {
        return "c"
    } else if (A == 0 && B == 1 && C == 0) {
        return "b"
    } else if (A == 1 && B == 0 && C == 0) {
        return "a"
    } else {
        val a = StringBuilder()
        val b = StringBuilder()
        val c = StringBuilder()

        if (A > 0) {
            a.append(solution(A-1, B, C))
            insertLetter(a, 'a')
        }
        if (B > 0) {
            b.append(solution(A, B-1, C))
            insertLetter(b, 'b')
        }
        if (C > 0) {
            c.append(solution(A, B, C-1))
            insertLetter(c, 'c')
        }

        return if (a.length >= b.length && a.length >= c.length) {
            a.toString()
        } else if (c.length >= b.length && c.length >= a.length) {
            c.toString()
        } else b.toString()
    }
}

private fun insertLetter(s: StringBuilder, letter: Char) {
    var count = 0
    if (s.length >= 2) {
        for (i in 0 until s.length - 1) {
            if (s[i] == letter) {
                count++
            } else {
                if (count < 2) {
                    s.insert(i, letter)
                    break
                }
                count = 0
            }
        }
    } else {
        s.insert(0,letter)
    }
}



