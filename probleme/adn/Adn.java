package adn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Adn {
	static int N;
	static List<Helpadn> list = new ArrayList<Helpadn>();
	static List<Integer> sol = new ArrayList<Integer>();

	/*
	 * Metoda de citire din fisier
	 * 
	 */

	public static void readData() throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader("adn.in"));
		N = Integer.parseInt(buff.readLine());
		for (int j = 0; j < N; j++) {
			StringTokenizer st = new StringTokenizer(buff.readLine());
			list.add(new Helpadn(Integer.parseInt(st.nextToken())));
			for (int i = 0; i < list.get(j).numItems; i++) {
				if (i == 0) {
					list.get(j).s1 = st.nextToken();
				} else if (i == 1) {
					list.get(j).s2 = st.nextToken();
				} else if (i == 2) {
					list.get(j).s3 = st.nextToken();
				} else if (i == 3) {
					list.get(j).s4 = st.nextToken();
				}
			}
			list.get(j).rez = st.nextToken();
		}
		buff.close();
	}

	/*
	 * Cu aceasta metoda apelez cele trei metode de mai jos pentru a afla daca
	 * din sirurile date se poate forma sirul dorit.
	 */

	public static void solution() {
		for (int i = 0; i < N; i++) {
			if (list.get(i).numItems == 1) {
				if (list.get(i).s1.equals(list.get(i).rez)) {
					sol.add(1);
				} else {
					sol.add(0);
				}
			} else if (list.get(i).numItems == 2) {
				sol.add(help1(list.get(i).s1, list.get(i).s2, list.get(i).rez));
			} else if (list.get(i).numItems == 3) {
				sol.add(help2(list.get(i).s1, list.get(i).s2, list.get(i).s3, list.get(i).rez));
			} else if (list.get(i).numItems == 4) {
				sol.add(help3(list.get(i).s1, list.get(i).s2, list.get(i).s3, list.get(i).s4,
						list.get(i).rez));
			}
		}
	}

	/*
	 * Metoda ce umple o matrice 2D cu 1 sau 0 . In cazul in care elementul din
	 * coltul dreapta jos o sa fie 1 atunci se poate forma solutia dorita din
	 * cele 2 siruri.
	 * 
	 */

	public static int help1(String s1, String s2, String rez) {
		int[][] matrix = new int[s1.length() + 1][s2.length() + 1];
		int aux;

		if (s1.length() + s2.length() != rez.length()) {
			return 0;
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				aux = i + j - 1;

				if (i == 0 & j == 0) {
					matrix[i][j] = 1;

				} else if (i == 0) {
					if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1] == 1) {
						matrix[i][j] = 1;
					} else {
						matrix[i][j] = 0;
					}

				} else if (j == 0) {
					if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j] == 1) {
						matrix[i][j] = 1;
					} else {
						matrix[i][j] = 0;
					}

				} else {
					if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j] == 1) {
						matrix[i][j] = 1;
					} else if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1] == 1) {
						matrix[i][j] = 1;

					} else {
						matrix[i][j] = 0;
					}

				}

			}
		}

		return matrix[s1.length()][s2.length()];

	}

	/*
	 * Metoda help2 se bazeaza pe acelasi principiu ca metoda help1 doar ca de
	 * data aceasta se umple o matrice 3D cu 1 sau 0.
	 */

	public static int help2(String s1, String s2, String s3, String rez) {
		int[][][] matrix = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
		int aux;

		if (s1.length() + s2.length() + s3.length() != rez.length()) {
			return 0;
		}

		for (int k = 0; k < matrix[0][0].length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					aux = i + j + k - 1;

					if (i == 0 & j == 0 & k == 0) {
						matrix[i][j][k] = 1;
					} else if (i == 0 & j == 0 & k != 0) {
						if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}
					} else if (i == 0 & k == 0) {
						if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}

					} else if (j == 0 & k == 0) {

						if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}

					} else if (i == 0 & j != 0 & k != 0) {
						if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k] == 1) {
							matrix[i][j][k] = 1;
						} else if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j - 1][k] == 1) {
							matrix[i][j][k] = 1;
						} else if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}
					} else if (i != 0 & j == 0 & k != 0) {
						if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k] == 1) {
							matrix[i][j][k] = 1;
						} else if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i - 1][j][k] == 1) {
							matrix[i][j][k] = 1;
						} else if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}
					} else if (i != 0 & j != 0 & k == 0) {

						if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k] == 1) {
							matrix[i][j][k] = matrix[i - 1][j][k];
						} else if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k] == 1) {
							matrix[i][j][k] = matrix[i][j - 1][k];
						} else {
							matrix[i][j][k] = 0;
						}

					} else {
						if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k] == 1) {
							matrix[i][j][k] = matrix[i - 1][j][k];
						} else if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k] == 1) {
							matrix[i][j][k] = matrix[i][j - 1][k];

						} else if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1] == 1) {
							matrix[i][j][k] = 1;
						} else {
							matrix[i][j][k] = 0;
						}
					}
				}
			}

		}

		return matrix[s1.length()][s2.length()][s3.length()];
	}

	/*
	 * Metoda ce se bazeaza tot pe umplerea unei matrici cu 0 sau 1 doar ca in
	 * cazul acesta matricea are 4 dimensiuni
	 */

	public static int help3(String s1, String s2, String s3, String s4, String rez) {
		int[][][][] matrix = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1][s4.length()
				+ 1];
		int aux;

		if (s1.length() + s2.length() + s3.length() + s4.length() != rez.length()) {
			return 0;
		}

		for (int l = 0; l < matrix[0][0][0].length; l++) {
			for (int k = 0; k < matrix[0][0].length; k++) {
				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < matrix[0].length; j++) {
						aux = i + j + k + l - 1;

						if (i == 0 & j == 0 & k == 0 & l == 0) {
							matrix[i][j][k][l] = 1;

						} else if (k != 0 & i == 0 & j == 0 & l == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}

						} else if (k == 0 & i == 0 & j == 0 & l != 0) {
							if (s4.charAt(l - 1) == rez.charAt(aux) & matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0 & i != 0 & j == 0 & l == 0) {
							if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0 & i == 0 & j != 0 & l == 0) {
							if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0 & l == 0) {
							if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0 & i == 0) {
							if (s4.charAt(l - 1) == rez.charAt(aux) & matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0 & j == 0) {
							if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (i == 0 & l == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (j == 0 & l == 0 & k != 0 & i != 0) {
							if (s1.charAt(i - 1) == rez.charAt(aux) & matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s3.charAt(k - 1) == rez.charAt(aux)
									& matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (i == 0 & j == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (i == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (j == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s1.charAt(i - 1) == rez.charAt(aux)
									& matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (k == 0) {
							if (s2.charAt(j - 1) == rez.charAt(aux) & matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s1.charAt(i - 1) == rez.charAt(aux)
									& matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else if (l == 0) {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s1.charAt(i - 1) == rez.charAt(aux)
									& matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}
						} else {
							if (s3.charAt(k - 1) == rez.charAt(aux) & matrix[i][j][k - 1][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s4.charAt(l - 1) == rez.charAt(aux)
									& matrix[i][j][k][l - 1] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s1.charAt(i - 1) == rez.charAt(aux)
									& matrix[i - 1][j][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else if (s2.charAt(j - 1) == rez.charAt(aux)
									& matrix[i][j - 1][k][l] == 1) {
								matrix[i][j][k][l] = 1;
							} else {
								matrix[i][j][k][l] = 0;
							}

						}

					}
				}
			}
		}

		return matrix[s1.length()][s2.length()][s3.length()][s4.length()];
	}

	/*
	 * Metoda de scriere in fisier
	 * 
	 */

	public static void writeSolution() throws IOException {
		FileWriter fw = new FileWriter("adn.out");
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < sol.size(); i++) {
			bw.write(String.valueOf(sol.get(i)));
			bw.newLine();
		}

		bw.close();

	}

	public static void main(String[] args) throws IOException {
		Adn.readData();
		Adn.solution();
		Adn.writeSolution();

	}

}
