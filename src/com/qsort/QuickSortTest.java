package com.qsort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

	@Test
	public void test() {
		QuickSort quickSort = new QuickSort("antiquicksort1M.txt");
		quickSort.sortLo();
		Assert.assertTrue(quickSort.isSorted());
	}

}
