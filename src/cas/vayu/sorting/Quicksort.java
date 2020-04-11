package cas.vayu.sorting;

import java.util.ArrayList;
import java.util.Comparator;

import cas.vayu.DisasterPoint;

import java.util.Collection;
import java.util.Collections;

public class Quicksort {

	public ArrayList<DisasterPoint> quicksort(ArrayList<DisasterPoint> list, int low, int high,
			Comparator<DisasterPoint> comp) {
		int ll = low;
		int hh = high;

		if (hh > ll) {
			DisasterPoint pivot = list.get((ll + hh) / 2);
			while (ll < hh) {
				while (ll < high && comp.compare(list.get(ll), pivot) < 0) {
					ll += 1;
				}
				while (hh > low && comp.compare(list.get(hh), pivot) > 0) {
					hh -= 1;
				}
				if (ll <= hh) {
					Collections.swap(list, ll, hh);
					ll += 1;
					hh -= 1;
				}
				if (low < hh) {
					quicksort(list, low, hh, comp);
				}
				if (ll < high) {
					quicksort(list, ll, high, comp);
				}

			}
		}
		return list;
	}

	public ArrayList<DisasterPoint> sort(ArrayList<DisasterPoint> list, Comparator<DisasterPoint> comp) {
		int low = 0;
		int high = list.size() - 1;
		return quicksort(list, low, high, comp);
	}

}
