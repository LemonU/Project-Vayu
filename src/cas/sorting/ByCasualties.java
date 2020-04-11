package cas.sorting;

import java.util.Comparator;
import cas.vayu.DisasterPoint;
public class ByCasualties implements Comparator<DisasterPoint> {

	/**
	 * Compares the number of casualties of two DisasterPoints
	 * 
	 * @param o1 The first DisasterPoint
	 * @param o2 The second DisasterPoint
	 * @return -1 if the first has less casualties, 0 if they have an equal amount,
	 *         1 if the first has more casualties
	 */
	@Override
	public int compare(DisasterPoint o1, DisasterPoint o2) {
		if (o1.getCasulaties() < o2.getCasulaties()) {
			return -1;
		}
		if (o1.getCasulaties() == o2.getCasulaties()) {
			return 0;
		}
		if (o1.getCasulaties() > o2.getCasulaties()) {
			return 1;
		}
		return 0;
	}

}
