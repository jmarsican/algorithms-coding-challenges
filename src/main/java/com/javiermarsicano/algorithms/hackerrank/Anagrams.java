package com.javiermarsicano.algorithms.hackerrank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams
 of each other if the first string's letters can be rearranged to form the second string. In other words, both strings
 must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc
 and dcbad are not.
 Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number
 of character deletions required to make the two strings anagrams. Can you help her find this number?
 Given two strings, A and B, that may or may not be of the same length, determine the minimum number of character
 deletions required to make A and B anagrams. Any characters can be deleted from either of the strings.
 For example, if A='cde' and B='dcf', we can delete 'e' from string A and 'f' from string B so that both remaining strings are 'cd' and 'dc' which
 are anagrams.
 * */

public class Anagrams {

	static void buildMap(String in, HashMap<Character, Integer> map, HashSet<Character> set) {
        for (int i = 0; i < in.length(); i++) {
            char elem = in.charAt(i);
            Integer countElem = map.get(elem);
            if (countElem == null) {
                map.put(elem, 1);
            } else {
                map.put(elem, countElem + 1);
            }
            set.add(elem);
        }
    }
    
    static int makeAnagram(String first, String second) {
        HashMap<Character, Integer> mapA  = new HashMap<Character, Integer>();
        HashMap<Character, Integer> mapB  = new HashMap<Character, Integer>();
        int countDel = 0;
        HashSet<Character> set = new HashSet<Character>();
        
        buildMap(first, mapA, set);
        buildMap(second, mapB, set);
        
        for (Character c : set) {
        	Integer a = mapA.get(c);
        	Integer b = mapB.get(c);
        	if (a == null) a = 0;
        	if (b == null) b = 0;
        	
            countDel = countDel + Math.abs(a - b);
        }
        
        return countDel;        
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(makeAnagram(a, b));
    }
}

