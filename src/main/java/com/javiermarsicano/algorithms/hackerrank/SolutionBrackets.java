package com.javiermarsicano.algorithms.hackerrank;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/*
a sequence of brackets is considered to be balanced if the following conditions are met:

- It contains no unmatched brackets. for example  {[]}
- The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.

Given  strings of brackets, determine whether each sequence of brackets is balanced. If a string is balanced, print YES on a new line; otherwise, print NO on a new line.
 * */
public class SolutionBrackets {
	
	public static boolean isBalanced(String brackets) {
		Stack<Character> stack = new Stack<Character>();
		HashMap<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		
		if (brackets.length() % 2 != 0)
			return false;
		
		for (int i = 0; i < brackets.length(); i++) {
			if (map.keySet().contains((brackets.charAt(i)))) {
				stack.push(brackets.charAt(i));
			} else if (stack.isEmpty()) {
				return false;
			} else if (map.get(stack.pop()) != brackets.charAt(i)) {
				return false;
			}
		}
		
		return stack.isEmpty();
	}
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
	}

}
