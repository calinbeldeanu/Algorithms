package permutari;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Permutari {
	static int N;
	static String sol;
	static int[] colour = new int[26];
	static List<Integer> ciclu = new ArrayList<Integer>();
	static Graph graph = new Graph(26);
	private static final int WHITE = 0;
	private static final int GREY = 1;
	private static final int BLACK = 2;
	static List<Integer> topologic_sort = new ArrayList<Integer>();
	static List<String> words = new ArrayList<String>();


	/*
	 * Metoda de cititre din fisier
	 * 
	 */

	public static void readData() throws IOException {
		BufferedReader buff = new BufferedReader(new FileReader("permutari.in"));
		N = Integer.parseInt(buff.readLine());


		for (int i = 0; i < N; i++) {
			words.add(buff.readLine());
		}
		buff.close();
	}
	
	public static int convert(char caracter){
		
		int convert = Character.getNumericValue(caracter) - 10;
		return convert;
	}
	
	public static void convertToSolution(){
		if("Imposibil".equals(sol)){
			return;
		}
		sol = "";
		for(int i= 25 ; i> -1 ; i--){
			sol =sol.concat(String.valueOf((Character.toChars(topologic_sort.get(i)+ 97))));
		}
	}
	
	public static void compare(String s1 , String s2){
		if(s1.length() > s2.length()){
			int i = 0;
			while(i < s2.length()){
				if(s1.charAt(i) == s2.charAt(i) && i != s2.length() -1){
					i++;
				}else if(s1.charAt(i+1) == s2.charAt(i) && i == (s2.length() -1)){
					sol = "Imposibil";
					break;
				}else{
					graph.addEdge(convert(s1.charAt(i)),convert(s2.charAt(i)));
					break;
				}
			}
		}else{
			int i = 0;
			while(i < s1.length()){
				if(s1.charAt(i) == s2.charAt(i) && i != s1.length() -1){
					i++;
				}else if(s1.charAt(i) == s2.charAt(i) && i == s1.length() -1){
					break;
				}else{
					graph.addEdge(convert(s1.charAt(i)),convert(s2.charAt(i)));
					break;
				}
			}
			
			
		}
	}
	
	public static void cicluri (int Node){
		if(ciclu.contains(Node)){
			sol = "Imposibil";
			return;
		}else{
		ciclu.add(Node);
		}
		Integer aux;
		Iterator<Integer> it = graph.adjList.get(Node).iterator();
		while(it.hasNext()){
			aux = it.next();
			
			if(colour[aux] == GREY){
				cicluri(aux);
			}
		}
	}
	
	
	public static void exploreNodes (int Node){
		colour[Node] = GREY;
		Integer aux;
		Iterator<Integer> it = graph.adjList.get(Node).iterator();
		while(it.hasNext()){
			aux = it.next();
			
			if(colour[aux] == WHITE){
				exploreNodes(aux);
			}else if(colour[aux] == GREY){
				cicluri(aux);
				if("Imposibil".equals(sol)){
					return;
				}
				
			}
			
			
			
		}
		topologic_sort.add(Node);
		colour[Node] = BLACK;
		return;
	}
	
	
	
	public static void dfs(){
		for(int i= 0 ; i<26 ; i++){
			if(colour[i] == WHITE){
				exploreNodes(i);
			}
		}
	}
	
	
	
	
	
	public static void solution() {
		 String first , second;
		 int i = 0;
		 while (i < N -1){
			 first = words.get(i);
			 second = words.get(i+1);
			 compare(first , second);
			 if("Imposibil".equals(sol)){
				 break;
			 }
			 i++;
		 }
		 if("Imposibil".equals(sol)){
			return;
		 }
		 dfs();
				//TODO : solutia se scrie in var sol
	
	}
	
	public static void writeSolution() throws IOException {
		FileWriter fw = new FileWriter("permutari.out");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sol);
		bw.close();

	}
	
	
	public static void main(String[] args) throws IOException {
		Permutari.readData();
		Permutari.solution();
		Permutari.convertToSolution();
		Permutari.writeSolution();
	}
	

}
