package permutari;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	public List<List<Integer>> adjList;
	
	public Graph(int numNodes) {
		adjList = new ArrayList<List<Integer>>();

		for (int nodeIdx = 0; nodeIdx < numNodes; nodeIdx++) {
			adjList.add(nodeIdx, new ArrayList<Integer>());
		}
	}
	
	void addEdge(int from, int to) {
		adjList.get(from).add(to);
	}

}
