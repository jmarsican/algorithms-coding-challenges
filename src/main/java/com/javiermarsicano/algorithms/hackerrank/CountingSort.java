package com.javiermarsicano.algorithms.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 Often, when a list is sorted, the elements being sorted are just keys to other values. For example, if you are 
 sorting files by their size, the sizes need to stay connected to their respective files. You cannot just take 
 the size numbers and output them in order, you need to output all the required file information.

The counting sort is used if you just need to sort a list of integers. Rather than using a comparison, you create 
 an integer array whose index range covers the entire range of values in your array to sort. Each time a value 
 occurs in the original array, you increment the counter at that index. At the end, run through your counting 
 array, printing the value of each non-zero valued index that number of times.
 */
public class CountingSort {
	
	static int[] sortOptimized(int[] array, int max) {
		int[] aux = new int[max+1];
		
		//initialize counters array
		for (int i = 0; i < aux.length; i++) {
			aux[i] = 0;
		}
		
		for (int i = 0; i < array.length; i++) {
			aux[array[i]]++;
		}
		
		int[] result = new int[array.length];

		int index = 0;
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[i]; j++) {
				result[index] = i;
				index++;
			}
		}

		return result;
	}

	/*See Cormen's book for an explanation of this algorithms*/
	static void sort(int[] array, int max) {
		int[] aux = new int[max+1];

		//initialize counters array
		for (int i = 0; i < aux.length; i++) {
			aux[i] = 0;
		}

		for (int i = 0; i < array.length; i++) {
			aux[array[i]]++;
		}

		for (int i = 1; i < aux.length; i++) {
			aux[i] += aux[i-1]; //count numbers less than the indicated
		}

		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[aux[array[i]]-1] = array[i]; //place the number in the final position
			aux[array[i]]--;
		}

		for (int i = 0; i < result.length; i++)
			System.out.print(result[i]);
	}


	static int[] countingSort(int[] arr) {
		int max = Arrays.stream(arr).max().getAsInt();
		return sortOptimized(arr, max);
	}

	public static void main(String[] args) {
		//int[] test = {1,6,4,7,5,5,2,4,1,0,7,4,};
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int[] test = new int[t];

		for (int i = 0; i < t; i++)
			test[i] = in.nextInt();

		int[] result = countingSort(test);
		Arrays.stream(result).forEach(System.out::println);
	}

}

