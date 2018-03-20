package points;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Points {
	static int N;
	static int M;
	static List<Interval> intervals = new ArrayList<Interval>();
	static List<Integer> points = new ArrayList<Integer>();
	static int value;
	static Interval test = new Interval(0, 0);
	static Interval helper = new Interval(0, 0);

	/*
	 * Metoda de cititre din fisier
	 * 
	 */

	public static void readData() throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader("points.in"));
		StringTokenizer st = new StringTokenizer(buff.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(buff.readLine());
		for (int i = 0; i < N; i++) {

			points.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(buff.readLine());
			intervals.add(new Interval(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		buff.close();
	}

	/*
	 * Metoda de sortare a intervalelor
	 * 
	 */

	public static void intervalsSort(List<Interval> list) {
		Collections.sort(list, new Interval());
	}

	/*
	 * Modul de solutionare al problemei , o sa explic mai multe in README.
	 * 
	 */

	public static void solution() {
		Integer min = points.get(0);
		Integer max = points.get(points.size() - 1);
		Integer capatmin = min;
		Interval good = new Interval(0, 0);
		Interval longest = new Interval(0, 0);
		int contor = 0;
		int size = intervals.size() - 1;
		int i;

		while (contor <= size) {
			while (test.low <= min) {
				if (contor == size) {
					test = intervals.get(contor);
					break;
				}
				test = intervals.get(contor);
				good = intervals.get(contor + 1);

				if (test.low <= min & good.low <= min & test.high <= max) {
					if ((test.high - test.low) - (good.high - good.low) > 0) {
						longest = test;
						contor++;
					} else {
						longest = good;
						contor++;
					}

				} else {
					if (longest.low != 0 & longest.high != 0) {
						test = longest;
					}
					contor++;
					break;
				}
			}

			if (test.low <= capatmin & max <= test.high) {
				value = 1;
				break;

			} else if (test.low <= min & test.high < max) {
				value++;
				min = test.high;
				i = 0;
				while (points.get(i) <= min) {
					i++;
				}
				min = points.get(i);

			} else if (test.low <= min & test.high >= max) {
				value++;
				break;
			} else if (test.low == min + 1 & test.high >= max) {
				value++;
				break;
			}
		}

	}

	/*
	 * Metoda ce scrie in fisierul de iesire.
	 */

	public static void writeSolution() throws IOException {

		FileWriter fw = new FileWriter("points.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(value));
		bw.close();

	}

	public static void main(String[] args) throws IOException {
		Points.readData();
		intervalsSort(intervals);
		Points.solution();
		Points.writeSolution();
	}

}
