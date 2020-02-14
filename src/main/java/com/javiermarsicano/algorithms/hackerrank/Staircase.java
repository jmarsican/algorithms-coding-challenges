package com.javiermarsicano.algorithms.hackerrank;

import java.util.Scanner;

public class Staircase {
	
	public static int stairs(int n, int lim, int[] lookup) {
		if (n == lim) {
			return 1;
		} else {
			int acum = 0;
			for (int i = 1; (i <= 3) && ((n + i) <= lim); i++) {
                if (lookup[n + i - 1] == -1) {
				    lookup[n + i - 1] = stairs(n + i, lim, lookup);
                }
                acum = acum + lookup[n + i - 1];
			}
			return acum;
		}
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int n[] = new int[s];
        
        for(int a0 = 0; a0 < s; a0++){
            n[a0] = in.nextInt();
        }       
        
        
        for(int a0 = 0; a0 < s; a0++){
            
            int[] lookup = new int[n[a0]];  
            for (int i = 0; i < n[a0]; i++) {
                lookup[i] = -1;
            }
            
            System.out.println(stairs(0, n[a0], lookup));
        }
    }
}
/*
 * 5
19
18
35
20
25
 * */
