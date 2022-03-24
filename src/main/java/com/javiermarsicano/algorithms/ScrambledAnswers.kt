package com.javiermarsicano.algorithms

/*
You are running a classroom and suspect that some of your students are passing around the answers to multiple choice questions
disguised as random strings.
Your task is to write a function that, given a list of words and a string, finds and returns the word in the list that is
scrambled up inside the string, if any exists. There will be at most one matching word. The letters don't need to be in
order or next to each other. The letters cannot be reused.

Example:
words = ['cat', 'baby', 'dog', 'bird', 'car', 'ax']
string1 = 'tcabnihjs'
find_embedded_word(words, string1) -> cat (the letters do not have to be in order)

string2 = 'tbcanihjs'
find_embedded_word(words, string2) -> cat (the letters do not have to be together)

string3 = 'baykkjl'
find_embedded_word(words, string3) -> None / null (the letters cannot be reused)

string4 = 'bbabylkkj'  -> baby
string5 = 'ccc'  -> None / null
string6 = 'breadmaking' -> bird

Complexity analysis variables:
W = number of words in `words`
S = maximal length of each word or string
*/

fun buildDictionary(scrambled: String): Map<Char,Int> {
    val lettersOccurrences = mutableMapOf<Char,Int>()
    for (letter in scrambled.toCharArray()) {
        val oc = lettersOccurrences[letter]
        if (oc != null) {
            lettersOccurrences[letter] = oc + 1
        } else {
            lettersOccurrences[letter] = 1 //This was the only one thing to fix. There was a 0 instead
        }
    }
    return lettersOccurrences
}

fun find_embedded_word(words: Array<String>, scrambled: String): String? {
    val scrambledOccurrences = buildDictionary(scrambled)

    for (word in words) {
        val dict = buildDictionary(word)

        var isMatchingWord = true
        for (letter in word.toCharArray()) {
            val letterOcc: Int? = dict[letter]
            if (letterOcc != null) {
                var scrOc: Int? = scrambledOccurrences[letter]
                if (scrOc == null) scrOc = 0
                if (letterOcc > scrOc) {
                    isMatchingWord = false
                }
            }
        }
        if (isMatchingWord) return word
    }

    return null
}

fun main(args : Array<String>) {
    val words = arrayOf( "cat", "baby", "dog", "bird", "car", "ax" )
    val string1 = "tcabnihjs"
    val string2 = "tbcanihjs"
    val string3 = "baykkjl"
    val string4 = "bbabylkkj"
    val string5 = "ccc"
    val string6 = "breadmaking"

    println(find_embedded_word(words, string4))
}
