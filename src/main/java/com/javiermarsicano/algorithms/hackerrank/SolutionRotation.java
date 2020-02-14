package com.javiermarsicano.algorithms.hackerrank;

import java.util.Scanner;

public class SolutionRotation {
    
    public static void rotate(int[] in, int[] result, int n, int k) {
        for (int i = n - 1; i >= 0; i--) {
            if (i < k)
                result[n-k+i] = in[i];
            else
                result[i-k] = in[i];
        } 
    }

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        int[] result = new int[n];
        
        rotate(a, result, n, k);
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
      
        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");
      
        System.out.println();
      
    }
}
