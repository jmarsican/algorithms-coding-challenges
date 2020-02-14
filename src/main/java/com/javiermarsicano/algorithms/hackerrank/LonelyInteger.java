package com.javiermarsicano.algorithms.hackerrank;

import java.util.Scanner;

public class LonelyInteger {

	public static int lonelyInteger(int[] a) {
        int value = 0;

    
        for (int i : a) {
        	value ^= i;
        }

        return value;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.println(lonelyInteger(a));
    }
}
