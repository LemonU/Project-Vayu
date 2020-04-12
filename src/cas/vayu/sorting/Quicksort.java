package cas.vayu.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import cas.vayu.fileio.DisasterPoint;

import java.util.Collection;
import java.util.Collections;

public class Quicksort {
	public static <T> ArrayList<T> sort(List<T> array, Comparator<T> c) {
		ArrayList<T> output = new ArrayList<T>();
		for(T e : array) {
			output.add(e);
		}
		int lo = 0;
		int hi = array.size() - 1;
		quicksort(output,lo,hi,c); 
		return output;
	}
	public static <T> ArrayList<T> sort(List<T> array,int lo, int hi, Comparator<T> c) {
		if(lo < 0 || hi >= array.size()) {
			throw new IllegalArgumentException("Array indices to sort must exist in the array");
		}
		ArrayList<T> output = new ArrayList<T>();
		for(T e : array) {
			output.add(e);
		}

		quicksort(output,lo,hi,c); 
		return output;
	}
	public static <T> void quicksort(ArrayList<T> list, int lo, int hi,
			Comparator<T> c) {
		if (hi <= lo) return;
		int j = partitionRandom(list,lo,hi,c); 
		quicksort(list,lo,j-1,c);
		quicksort(list,j+1,hi,c);
		
	}
	private static <T> int partitionRandom(ArrayList<T> list, int lo, int hi,
			Comparator<T> c) {
		int r = (int) (Math.random() *((hi-lo)+1)+lo);
		exch(list,lo,r);
		return partition(list,lo,hi,c);
	}
	private static <T> int partition(List<T> list,int lo, int hi, Comparator<T> c) {
		int i = lo, j = hi+ 1;
		T v = list.get(lo);
		while(true) {
			while(c.compare(list.get(++i), v) < 0) if (i == hi) break;
			while(c.compare(v, list.get(--j)) < 0) if (j == lo) break;
			if(i >= j) break;
			exch(list,i,j);
		}
		exch(list,lo,j);
		return j;
	}
	private static <T> void exch(List<T> list, int i1, int i2) {
		T temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
	}


}
