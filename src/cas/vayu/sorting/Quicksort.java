package cas.vayu.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Quicksort class which provides static sorting methods which return a sorted copy
 * of an ArrayList
 * @author Oussama Saoudi
 * @author Diego Soriano
 *
 */
public class Quicksort {
	/**
	 * Copies and sorts list of type T, and returns the copy. Uses comparator c
	 * to compare values in the list. 
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param c Comparator used to compare the values in the list
	 * @return the sorted copy of the List list
	 */
	public static <T> ArrayList<T> sort(List<T> list, Comparator<T> c) {
		ArrayList<T> output = new ArrayList<T>();
		for(T e : list) {
			output.add(e);
		}
		int lo = 0;
		int hi = list.size() - 1;
		quicksort(output,lo,hi,c); 
		return output;
	}
	/**
	 * Copies and sorts list of type T, and returns the copy. Uses comparator c
	 * to compare values in the list. 
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param lo low end index of area in list to sort
	 * @param hi hi end index of area in list to sort
	 * @param c Comparator used to compare the values in the list
	 * @return the sorted copy of the List list
	 * @throws IllegalArgumentException if the indexs lo and hi are outside of the
	 *  bounds of the list
	 */
	public static <T> ArrayList<T> sort(List<T> list,int lo, int hi, Comparator<T> c) {
		if(lo < 0 || hi >= list.size()) {
			throw new IllegalArgumentException("Array indices to sort must exist in the array");
		}
		ArrayList<T> output = new ArrayList<T>();
		for(T e : list) {
			output.add(e);
		}

		quicksort(output,lo,hi,c); 
		return output;
	}
	/**
	 * Performs quicksort by partitioning the list and recursively sorting the
	 * sub lists
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param lo low end index of area in list to sort
	 * @param hi hi end index of area in list to sort
	 * @param c Comparator used to compare the values in the list
	 */
	public static <T> void quicksort(ArrayList<T> list, int lo, int hi,
			Comparator<T> c) {
		if (hi <= lo) return;
		int j = partitionRandom(list,lo,hi,c); 
		quicksort(list,lo,j-1,c);
		quicksort(list,j+1,hi,c);
		
	}
	/**
	 * Switches the first value in a sublist with a random value in that sublist
	 * to improve sorting performance
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param lo low end index of area in list to sort
	 * @param hi hi end index of area in list to sort
	 * @param c Comparator used to compare the values in the list
	 * @return Returns the index of the partitioning piont in the sorting
	 */
	private static <T> int partitionRandom(ArrayList<T> list, int lo, int hi,
			Comparator<T> c) {
		int r = (int) (Math.random() *((hi-lo)+1)+lo);
		exch(list,lo,r);
		return partition(list,lo,hi,c);
	}
	/**
	 * Partitions values in a list into values less than the first item in the list and
	 * elements greater than the the first element
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param lo low end index of area in list to sort
	 * @param hi hi end index of area in list to sort
	 * @param c Comparator used to compare the values in the list
	 * @return Returns the index of the partitioning piont in the sorting
	 */
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
	/**
	 * Exchanges two values in a list at indexes i1 and i2
	 * @param <T> Type of the values in the List
	 * @param list The List of type T to sort and return
	 * @param i1 First index of the swap
	 * @param i2 Second index of the swap
	 */
	private static <T> void exch(List<T> list, int i1, int i2) {
		T temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
	}


}
