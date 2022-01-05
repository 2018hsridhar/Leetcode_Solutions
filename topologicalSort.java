Topological Sort
You're given a list of arbitrary jobs that need to be completed; these 
jobs are represented by distinct integers. You're also given a list of 
dependencies. A dependency is represented as a pair of jobs where the 
first job is a prerequisite of the second one. In other words, the second 
job depends on the first one; it can only be completed once the first 
job is completed.

Write a function that takes in a list of jobs and a list of dependencies 
and returns a list containing a valid order in which the given jobs can 
be completed. If no such order exists, the function should return an 
empty array.

Sample Input
jobs = [1, 2, 3, 4]			( are they ID's, or guaranteed within )? always 1 -> N
deps = [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]

Sample Output
[1, 4, 3, 2] or [4, 1, 3, 2]

Clarifications
- Jobs are integers
- edges represented as pairs of jobs : (prereq, successor)
- takes in a list of jobs and their dependencies
- returns a valid order in which jobs are completeable
- order non-existent : ret empty array
- assume everything fit in memory
- assume no parallel edges or self-loops
- the graph is a DAG ( can be cyclic )
- can we have disjoint graphs
- K_N : complete graph

Is cycle detection needed

Kahn's algorithm

PSEUDOCODE :

	Create adjacent list from the edge list
	HM adjList<Integer,Set<Integer>>	# bitset
	compute in degrees of each node from the adjacent list to a new hash set
	HM inDegs<Integer,Integer>
	
	topOrder = ()
	queue = ()
	for each node v in inDegs
		if inDeg(v) == 0
			queue.push(v)
			
	# queue = nodes with indegree = 0
	visited = ()
	while queue is not empty			// O(V)
		X = queue.pop
		visited.add(X)
		hood(X) <- adjList[X]
		topOrder.append(X)
		
		for each node v in hood(X)			// O(max_number_edges_per_vertex)
			inDeg[v] -= 1
			if inDeg[v] equals 0
				queue.push(v)
			
	# Queue is empty
	if(size(visited) != V)
		ret false
	ret true
Lvl-by-lvl, with queues => BFS
	
Complexity : 
	
	space precomp = O(V*E) = O(V^2)
	space Algo = O(V)
	
	time precomp = O(E) + O(E)
	time algo = O(V) + O(V*E) 
	
	
	avg :
	TIME = O(V+E) :: worst, it is O(V^2) if K_N
	SPACE = O(1) ( imp ) O(V+E) ( exp )
	
	worst :
	TIME = O(V^2)
	SPACE = O(V^2)
	
	
	

	



Complexity
Let V := #-jobs
Let E := #-dependencies

TEST CASES : 
(A) [1, 2, 3, 4]
[
  [1, 2],
  [2, 3],
  [3, 4],
  [4, 1]
]
(B) [0,1, 2, 3, 4]
[
  [0,1],
  [1, 2],
  [2, 3],
  [3, 4],
  [4, 1]
]
(C) [1, 2, 3, 4]
[
  [1, 2],
  [3, 4]
]


import java.util.*;

class Program {

	private static HashMap<Integer,Set<Integer>> createAdjList(List<Integer> jobs, List<Integer[]> deps)
	{
		HashMap<Integer,Set<Integer>> adjList = new HashMap<Integer,Set<Integer>>();
	    for(Integer job : jobs)
			adjList.put(job, new HashSet<Integer>());
		for(int i = 0; i < deps.size(); ++i)
		{
			Integer[] edge = deps.get(i);
			int src = edge[0];
			int dst = edge[1];
			if(adjList.containsKey(src))
			{
				adjList.get(src).add(dst);
			}
		}
		
		return adjList;
	}
	
	private static HashMap<Integer,Integer> computeInDegrees(List<Integer> jobs, List<Integer[]> deps)
	{
		HashMap<Integer,Integer> inDegs = new HashMap<Integer,Integer>();
	    for(Integer job : jobs)
			inDegs.put(job, 0);
		
		for(int i = 0; i < deps.size(); ++i)
		{
			Integer[] edge = deps.get(i);
			int src = edge[0];
			int dst = edge[1];
			if(inDegs.containsKey(src))
			{
			   inDegs.put(dst, inDegs.get(dst) + 1);
			}
		}
		return inDegs;
	}
	
  public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) 
  {
	int V = jobs.size();
  	int E = deps.size();
	  
	HashMap<Integer,Set<Integer>> adjList = createAdjList(jobs, deps);
	HashMap<Integer,Integer> inDegs = computeInDegrees(jobs, deps);
	List<Integer> topOrder = new ArrayList<Integer>();
    Queue<Integer> traversal = new LinkedList<Integer>();
	
	Set<Integer> keysInDegs = inDegs.keySet();
  	Iterator<Integer> keyItr = keysInDegs.iterator();
    while(keyItr.hasNext())
	{
		int v = keyItr.next();
		if(inDegs.get(v) == 0)
		{
			traversal.add(v);
		}
	}
			
    int numElemsEncount = 0;
	while(!traversal.isEmpty())
	{
		// [1] Work of parent
		int curr = traversal.poll();
		numElemsEncount++;
		Set<Integer> hood = adjList.get(curr);
		topOrder.add(curr);
		
		// [2] Work of chidlren
		for(Integer child : hood)
		{
			inDegs.put(child, inDegs.get(child) - 1);
			if(inDegs.get(child) == 0)
			{
				traversal.add(child);
			}
		}
	}
	  
	if(numElemsEncount != V)
		return new ArrayList<Integer>();
	return topOrder;
	
  }
}
