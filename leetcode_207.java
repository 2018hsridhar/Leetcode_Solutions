

class Solution {
    
    /*
    
    Is a topological sort algorithm, in nature
    What to do in event that topological sorting crashes due to the existence of a cycle in the graph?
    
    numCourses = prequisities.length
    Can we produce a complete topological ordering here?
    All pairings unique - no double counting of (a,b) pairs here :-O 
    Maybe top fails if multiple incidental edges on a given vertex?
    
    EDGE CASES : 
    1. Graph has cycle
    2. numCourses = 1, or prequisites.length == 0
    
    
    Can the graph structure, from pairings, be built-up here, or not?
    Provided an edge list here
    Can 0-index the courses too for proper counting
    
    */

  /*

    URL = https://leetcode.com/problems/course-schedule/

    THOUGHT PROCESSES

    1. Is a topological sort algorithm - code up topological sort in order to solve the problem
    2. Topological sort is built on DFS
    3. Vertex visited arrays/sets help with having to choose which new vertex to visit 
    4. Since we can have many topological sorts - we can choose any vertex! Order remains arbitrary
    Fill up ordering, in reverse, via recursive call stacks!
    Non-cyclical, directed graphs ( includes trees ) do not neccessarily have an order relation - a top sort helps us set up an order relation
    Based on an implementation of Kahn's Algorithm
    5. Is there a means, in the random selection of initial nodes, to pick a node with no dependencies?

    IDEAL : [T,S] = {O(V),O(E),O(V+E)} timings for both - a type of linear time
    - Notice how Big-O complexity, for these problems, tend to be of three types. Ideally, we choose O(V) or O(E), based on the number of vertices or edges of the graph.
    CURRENT : 

    Base cases 

    Edge Cases

    Gotchas

    TWO Approaches to attempt coding : 
    1. The normal approach : works backwards
    2. Kahn's algorithms - produces ordering in forward approach, via in-degrees. But requires 'a priori' knowledge, and not as intuitive
    - drawback : needs in-degree information known ahead of time to be constructed, and additional space allocated.

    Resources for Topological sorting : 
    1. https://www.youtube.com/watch?v=eL-KzMXSXXI&list=PLDV1Zeh2NRsDGO4--qE8yH72HFL1Km93P&index=16
    2. https://www.youtube.com/watch?v=cIBFEhD77b4&list=PLDV1Zeh2NRsDGO4--qE8yH72HFL1Km93P&index=17

    */
    
    // #TODO : attempt to code the normal approach. Kahn's approach was coded here.
    
    
     public boolean canFinish(int numCourses, int[][] prerequisites) 
    {
        boolean canFinish = false;
        
        // CONSTRUCT adjacency-list representation
        // OBTAIN INDEGREES OF EACH NODE
        ArrayList<Integer> adjList[] = new ArrayList[numCourses];
        int[] in_degree = new int[numCourses];
        
        // ZERO INITIALIZE THE INDEGREES VARIABLE, JUST IN CASE
        for(int i = 0; i < numCourses; ++i)
            in_degree[i] = 0;
        
        for (int i = 0; i < numCourses ; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < prerequisites.length; ++i)
        {
            int[] edge = prerequisites[i];
            int a = edge[0];
            int b = edge[1];
            in_degree[b] += 1; 
            adjList[a].add(b);
        }
   
// USEFUL DEBUGGING
//         for(int i = 0; i < numCourses; ++i)
//         {
//             System.out.printf("Indegree of node [%d] = [%d]\n", i, in_degree[i]);
//         }
        
//         for(int i = 0; i < numCourses; ++i)
//         {
//             System.out.printf("For node [%d], adj list = ", i);
//             for(int j = 0; j < adjList[i].size(); ++j)
//             {
//                 int val =(int) adjList[i].get(j);
//                 System.out.printf(" %d\t", val);
//             }
//             System.out.println();
//         }
        
        // NOW set the queue, and initialize it with nodes of zero in-degree value
        Queue<Integer> myQ = new LinkedList();
        for(int i = 0; i < numCourses; ++i)
        {
            if(in_degree[i] == 0)
                myQ.add(i);
        }
        
        // Test if a topological ordering can be induced
        int index = 0;
        int[] ordering = new int[numCourses];
        while(!myQ.isEmpty())
        {
            int at = myQ.remove();
            ordering[index] = at;
            index++;
            ArrayList<Integer> neighbors = adjList[at];
            for(int nx : neighbors)
            {
                in_degree[nx] = in_degree[nx] - 1; // remove current node : subtract in-degree here
                if(in_degree[nx] == 0)
                {
                    myQ.add(nx);
                }
            }
        }
        
        
        if(index != numCourses)
            return false;
        
        return true;
    }
}
