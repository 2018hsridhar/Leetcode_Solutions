// https://www.algoexpert.io/questions/Task%20Assignment
// Corresponds to an AlgoExpert.IO problem

import java.util.*;

class Program 
{
	/*
		THOUGHT PROCESS : 
		
		k >= 1 ( at least one worker, and at least 2 tasks ) 
		Assume k is reasonably bounded too
		Tasks ArrayList input denotes time taken for each tasks ( we can sort it too ? ) 
		Return the task assignemnt ( optimal indices ) 
		Tasks durations are NOT unique though!
		Category -> greedy algo : sort initial input usually
		
		COMPLEXITY : 
		TIME = O(NlogN) space
		SPACE = O(N) space : storing hashmap and indices via LinkedList data structure
		Referneces are really powerful too : address.function() invocation is going on UNDER THE HOOD() for objects ( e.g. arraylist.addLast())
		
		
		
		Do NOT make the mistake of mapping this to the 2SUM problem either
		Guaranteed an even-length array too : utilize two pointers meet-in-the-middle approach
		
		DATA STRUCTURES ARE OBJECTS IN JAVA ( INSTANITATED WITH "NEW" HERE : INDEED ) 
		
		
	*/
	// We know how to get the longest time, for sure
	// 
  public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) 
	{
		ArrayList<ArrayList<Integer>> assignment = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer, LinkedList<Integer>> hm = new HashMap<Integer, LinkedList<Integer>>();
		for(int i = 0; i < tasks.size(); ++i)
		{
			int key = tasks.get(i);
			LinkedList<Integer> cur;
			if(!hm.containsKey(key))
				cur = new LinkedList<Integer>();
			else
				cur = hm.get(key);
			cur.addLast(i);
			hm.put(key, cur);
		}
		
		// Iterate over tasks ArrayList
		Collections.sort(tasks);
		for(int i = 0; i < (tasks.size()/2); ++i)
		{
			int task_one = tasks.get(i);
			int task_two = tasks.get(tasks.size() - 1 - i);
			ArrayList<Integer> newTaskPair = new ArrayList<Integer>();
			newTaskPair.add(hm.get(task_one).getFirst());
			newTaskPair.add(hm.get(task_two).getFirst());
			hm.get(task_one).removeFirst();
			hm.get(task_two).removeFirst();
			assignment.add(newTaskPair);
		}
		
		return assignment;
  }
}
