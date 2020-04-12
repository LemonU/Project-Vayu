package cas.vayu.sorting;

import java.util.Comparator;

import cas.vayu.fileio.DisasterPoint;
/**
 * Comparator which compares two DisasterPoints by their property damage
 * @author Diego Soriano
 *
 */
public class ByDamage implements Comparator<DisasterPoint> {

	/**
	 * Compares the damage of two DisasterPoints
	 * 
	 * @param o1 The first DisasterPoint
	 * @param o2 The second DisasterPoint
	 * @return -1 if the first has less damage, 0 if they have an equal amount,
	 *         1 if the first has more damage
	 */
	@Override
	public int compare(DisasterPoint o1, DisasterPoint o2) {
		if (o1.getPropertyDamage() < o2.getPropertyDamage()) {
			return -1;
		}
		if (o1.getPropertyDamage() == o2.getPropertyDamage()) {
			return 0;
		}
		if (o1.getPropertyDamage() > o2.getPropertyDamage()) {
			return 1;
		}
		return 0;
	}

}