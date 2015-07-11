package com.qsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {

	private static final int CUTOFF = 10;
	private int array[];
	private Random random;

	public QuickSort(int[] array) {
		this.array = array;
		random = new Random();
	}

	public QuickSort(String fileName) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				list.add(scanner.nextInt());
			}
			final int size = list.size();
			this.array = new int[size];
			for (int i = 0; i < size; i++) {
				this.array[i] = list.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	private void sortLo(int lo, int hi) {
		if (hi > lo) {
			if (hi - lo + 1 > CUTOFF) {
				int pivot = partitionLo(lo, hi);
				if (lo < pivot - 1) {
					sortLo(lo, pivot - 1);
				}
				if (hi > pivot + 1) {
					sortLo(pivot + 1, hi);
				}
			} else {
				insertionSort(lo, hi);
			}
		}
	}

	public void sortLo() {
		if (array == null || array.length < 2) {
			return;
		}
		sortLo(0, array.length - 1);
	}

	private int partitionLo(int lo, int hi) {
		int pivot = array[lo];
		int i = lo;
		int j = hi + 1;
		while (true) {
			// Here we need to check if we didn't run off the right end
			while (array[++i] < pivot) {
				if (i == hi) {
					break;
				}
			}

			// We don't need to check if didn't run off the left end because
			// a[lo] equals to itself.
			// So we stop anyway
			while (array[--j] > pivot)
				;

			if (i >= j) {
				break;
			}
			swap(i, j);
		}
		swap(lo, j);
		return j;
	}

	private void sortHi(int lo, int hi) {
		if (hi > lo) {
			if (hi - lo + 1 > CUTOFF) {
				int pivot = partitionHi(lo, hi);
				if (lo < pivot - 1) {
					sortHi(lo, pivot - 1);
				}
				if (hi > pivot + 1) {
					sortHi(pivot + 1, hi);
				}
			} else {
				insertionSort(lo, hi);
			}
		}
	}

	public void sortHi() {
		if (array == null || array.length < 2) {
			return;
		}
		sortHi(0, array.length - 1);
	}

	private int partitionHi(int lo, int hi) {
		int pivot = array[hi];
		int i = lo - 1;
		int j = hi;
		while (true) {
			// We don't need to check if didn't run off the right end because
			// a[hi] equals to itself.
			// So we stop anyway
			while (array[++i] < pivot)
				;

			// Here we need to check if we didn't run off the left end
			while (array[--j] > pivot) {
				if (j == lo) {
					break;
				}
			}

			if (i >= j) {
				break;
			}
			swap(i, j);
		}
		swap(i, hi);
		return i;
	}

	private void insertionSort(int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			int j = i - 1;
			int temp = array[i];
			while (j >= lo && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
	}

	private void swap(int p, int q) {
		int t = array[p];
		array[p] = array[q];
		array[q] = t;
	}

	public void shuffleArray() {
		for (int i = 0; i < array.length; i++) {
			swap(i, random.nextInt(array.length));
		}
	}

	public boolean isSorted() {
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				return false;
			}
		}
		return true;
	}
	
	// TODO: Three-way partitioning
	// Median of three
	// RAndomized quickSort
	// Test shuffle
	// O(n) for all equal items in the input array
}
