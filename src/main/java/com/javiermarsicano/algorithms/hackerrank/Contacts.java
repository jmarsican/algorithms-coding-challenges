package com.javiermarsicano.algorithms.hackerrank;

import java.util.HashMap;
import java.util.Scanner;

public class Contacts {

	static class Node {
		HashMap<Character, Node> children;
		boolean isCompleteWord;
		
		public Node() {
			children = new HashMap<Character, Node>();
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
			add(name.substring(1, name.length()), root.children.get(name.charAt(0)));
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
				return find(name.substring(1, name.length()), root.children.get(name.charAt(0)));
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
