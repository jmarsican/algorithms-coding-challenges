package com.javiermarsicano.algorithms.hackerrank;

//Mal planteado - rehacer
public class SolutionMergeSort {
	
	public static void mergesort(int[] array) {
		mergeSortRecur(array, 0, array.length - 1);
	}
	
	public static int[] mergeHalves(int[] array, int start, int end) {
		int[] result = new int[end - start];
		
		int indexA, indexB, count;
		final int half = (end + start) / 2;
		indexA = start;
		indexB = half;
		count = 0;
		
		while (indexA < half && indexB < end) {
			if (array[indexA] > array[indexB]) {
				result[count] = array[indexA];
				indexA++;
			} else {
				result[count] = array[indexB];
				indexB++;
			}
			count++;
		}
		
		//pick remaining elems
		if (indexA < half) {
			for (int i = indexA; i < half; i++) {
				result[count] = array[indexA];
				indexA++;
				count++;
			}
		} else {
			for (int i = indexB; i < end ; i++) {
				result[count] = array[indexB];
				indexB++;
				count++;
			}
		}
		
		//copy whole result on actual array
		count = 0;
		for (int i = start; i < end; i++) {
			array[i] = result[count];
			count++;
		}		
		
		
		return result;
	}
	
	public static void mergeSortRecur(int[] array, int start, int end) {
		if (end <= start) {
			return;
		} else {
			//divide
			final int half = (start + end ) /2;
			
			mergeSortRecur(array, start, half);
			mergeSortRecur(array, half +1, end);
			//conquer
			mergeHalves(array, start, end);
		}
	}
	
	public static void main(String[] args) {
		int[] test = {9,5,3,10,3,2,1};
		mergesort(test);
	}
}
