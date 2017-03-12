import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int t = s.nextInt();

		for (int i = 0; i < t; i++) {

			int m = s.nextInt();
			int n = s.nextInt();

			int[] hori = new int[m - 1];
			for (int j = 0; j < m - 1; j++) {
				hori[j] = s.nextInt();
			}
			hori = mergeSort(hori);
			
			int[] verti = new int[n - 1];
			for (int j = 0; j < n - 1; j++) {
				verti[j] = s.nextInt();
			}
			verti = mergeSort(verti);
			
			long sum = solve(hori, verti);
			System.out.println(sum);
		}

		s.close();
	}

	private static long solve(int[] hori, int[] verti) {

		long sum = 0;
		int hIndex = 0, vIndex = 0, hBlocks = 1, vBlocks = 1;
		while (hIndex < hori.length && vIndex < verti.length) {
			if (hori[hIndex] > verti[vIndex]) {
				sum = sum + (long)vBlocks * (long)hori[hIndex];
				sum = sum % 1000000007;
				//System.out.println("sum: "+sum);
				hIndex++;
				hBlocks++;
			} else {
				sum = sum + (long)hBlocks * (long)verti[vIndex];
				sum = sum % 1000000007;
				//System.out.println("sum: "+sum);
				vIndex++;
				vBlocks++;
			}
		}

		if (hIndex == hori.length) {
			while (vIndex < verti.length) {
				sum = sum + (long)hBlocks * (long)verti[vIndex];
				sum = sum % 1000000007;
				//System.out.println("sum: "+sum);
				vIndex++;
				vBlocks++;
			}
		} else {
			while (hIndex < hori.length) {
				sum = sum + (long)vBlocks * (long)hori[hIndex];
				sum = sum % 1000000007;
				//System.out.println("sum: "+sum);
				hIndex++;
				hBlocks++;
			}
		}
		return sum;
	}

	private static int[] mergeSort(int[] array) {

		if (array.length > 1) {
			int len = array.length;
			int[] left, right;
			left = new int[len / 2];
			if (len % 2 == 0) {
				right = new int[len / 2];
			} else {
				right = new int[(len / 2) + 1];
			}

			for (int i = 0; i < len / 2; i++) {
				left[i] = array[i];
			}

			for (int i = len / 2; i < len; i++) {
				right[i - (len / 2)] = array[i];
			}

			left = mergeSort(left);
			right = mergeSort(right);
			int[] sorted = merge(left, right);
			return sorted;
		} else {
			return array;
		}

	}

	private static int[] merge(int[] left, int[] right) {

		int[] sortedArray = new int[left.length + right.length];
		int index = 0, leftIndex = 0, rightIndex = 0;
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex] > right[rightIndex]) {
				sortedArray[index] = left[leftIndex];
				leftIndex++;
			} else {
				sortedArray[index] = right[rightIndex];
				rightIndex++;
			}
			index++;
		}

		if (leftIndex == left.length) {
			while (rightIndex < right.length) {
				sortedArray[index] = right[rightIndex];
				rightIndex++;
				index++;
			}
		} else {
			while (leftIndex < left.length) {
				sortedArray[index] = left[leftIndex];
				leftIndex++;
				index++;
			}
		}

		return sortedArray;
	}

}
