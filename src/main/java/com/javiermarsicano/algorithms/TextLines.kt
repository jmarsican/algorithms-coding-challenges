package com.javiermarsicano.algorithms

/*
* Write a program that takes an input string and prints it as multiple lines of text such that no line o text is greater
* than N characters and words are kept whole.
*
* For example, below is the first line of the Gettysburg address. Each line of text must be no longer than N = 13 characters
* without breaking words.
*
* "13
* Four score and seven years ago our fathers brought forth upon this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal."
*
* When modified, the text looks as follows:
*
* "Four score
* and seven
* years ago our
* fathers
* brought forth
* upon this
* continent a
* new nation,
* conceived in
* Liberty and
* dedicated to
* the
* proposition
* that all men
* are created
* equal."
* */

fun main(args: Array<String>) {
    var inputData = ""
    var line = readLine()
    inputData += line + "\n"

    line = readLine()
    inputData += line + "\n"

    // Output the solution
    println(codeHere(inputData))
}

fun codeHere(inputData: String) : String {
    val maxChar = inputData.split("\n").first().toInt()
    val words = inputData.split("\n")[1].split(" ")

    val stringBuilder = StringBuilder()
    var count = 0 //number of characters in current line
    for (word in words) {
        if (word.length + count <= maxChar) { //append within the same line
            stringBuilder.append("$word ")
        } else { //insert new line
            stringBuilder.append("\n$word ")
            count = 0
        }
        count += word.length + 1
    }

    return stringBuilder.toString()
}