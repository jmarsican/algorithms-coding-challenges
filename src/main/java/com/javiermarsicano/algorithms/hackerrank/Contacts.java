package com.javiermarsicano.algorithms.hackerrank;

import java.util.HashMap;
import java.util.Scanner;

/**
 Implement two operations:
 1. add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
 2. find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
 Given  sequential add and find operations, perform each operation in order.
 */

public class Contacts {

	static class Node {
		HashMap<Character, Node> children;
		boolean isCompleteWord;
		
		public Node() {
			children = new HashMap<>();
		}		
	}
	
	static void add(String name, Node root) {
		if (!name.isEmpty()) {
			if (!root.children.containsKey(name.charAt(0))) {
				root.children.put(name.charAt(0), new Node());
			}
			if (name.length() == 1) {
				root.isCompleteWord = true;
			}
			add(name.substring(1), root.children.get(name.charAt(0)));
		}
	}
	
	static int traverse(Node root) {
		if (root == null || root.children.isEmpty()) {
			return 0;
		} else {
			int acum = root.isCompleteWord ? 1 : 0;
			for (Character child : root.children.keySet()) {
				acum = acum + traverse(root.children.get(child));
			}
			return acum;
		}
	}
	
	static int find(String name, Node root) {
		if(name.length() == 1) {
			int childMatch = traverse(root.children.get(name.charAt(0)));
			if (root.isCompleteWord) childMatch++;
			return childMatch;
		} else {
			if (!root.children.containsKey(name.charAt(0))) {
				return 0;
			} else {
				return find(name.substring(1), root.children.get(name.charAt(0)));
			}
		}
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        Node root = new Node();
        
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if ("add".equals(op))
            		add(contact, root);
            		
            if ("find".equals(op))
    			System.out.println(find(contact, root));
            
            
        }
    }
}
