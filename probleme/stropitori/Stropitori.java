package stropitori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Stropitori {

	static int nrStropitori;
	static int lengthS;
	static List<Stropitoare> stropitori = new ArrayList<Stropitoare>();
	static int value = 0;

	/*
	 * Metoda de citire din fisier al datelor.
	 */

	public static void readData() throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader("stropitori.in"));
		StringTokenizer st = new StringTokenizer(buff.readLine());

		st = new StringTokenizer(buff.readLine());
		nrStropitori = Integer.parseInt(st.nextToken());
		lengthS = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(buff.readLine());
		for (int i = 0; i < nrStropitori; i++) {
			stropitori.add(new Stropitoare(Integer.parseInt(st.nextToken()), 0));
		}

		st = new StringTokenizer(buff.readLine());
		for (int i = 0; i < nrStropitori; i++) {
			stropitori.get(i).jet = Integer.parseInt(st.nextToken());
		}
		buff.close();
	}

	/*
	 * Metoda de solutionare a problemei , o sa detaliez in README
	 */

	public static void solution() {
		Stropitoare x = new Stropitoare(0, 0);
		Stropitoare y = new Stropitoare(0, 0);
		long min = 0;
		long max = lengthS;

		for (int i = 0; i < stropitori.size(); i++) {
			if (i == stropitori.size() - 1) {
				x = stropitori.get(i);
				if (x.pos - x.jet > min) {
					value++;

				} else if (x.pos + x.jet <= max) {
					value++;
				}

			} else if (i == 0) {
				x = stropitori.get(i);
				y = stropitori.get(i + 1);
				if (x.pos - x.jet >= min) {
					value++;
					min = x.pos;
				} else if (x.pos + x.jet < y.pos) {
					value++;
					min = x.pos + x.jet;
				} else {
					min = x.pos;
				}

			} else {
				x = stropitori.get(i);
				y = stropitori.get(i + 1);
				if (x.pos - x.jet > min) {
					value++;
					min = x.pos;

				} else if (x.pos + x.jet < y.pos) {
					value++;
					min = x.pos + x.jet;
				} else {
					min = x.pos;
				}

			}
		}

	}

	/*
	 * Metoda de scriere in fisier
	 */

	public static void writeSolution() throws IOException {

		FileWriter fw = new FileWriter("stropitori.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(value));
		bw.close();

	}

	public static void main(String[] args) throws IOException {
		Stropitori.readData();
		Stropitori.solution();
		Stropitori.writeSolution();

	}

}
