package points;

import java.util.Comparator;

public class Interval implements Comparator<Interval>, Comparable<Interval> {
	int low;
	int high;

	public Interval(int low, int high) {
		this.low = low;
		this.high = high;

	}

	public Interval() {

	}

	@Override
	public int compareTo(Interval o) {
		return 0;
	}

	@Override
	public int compare(Interval o1, Interval o2) {
		if (o1.low == o2.low) {
			return (o1.high - o1.low) - (o2.high - o2.low);
		}

		return (o1.low - o2.low);
	}

}
