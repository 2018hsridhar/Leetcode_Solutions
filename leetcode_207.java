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

IDEAL : [T,S] = [O(V+E),O(V)]
CURRENT : 

Base cases 

Edge Cases

Gotchas

TWO Approaches to attempt coding : 
1. The normal approach : works backwards
2. Kahn's algorithms - produces ordering in forward approach, via in-degrees. But requires 'a priori' knowledge, and not as intuitive

Resources for Topological sorting : 
1. https://www.youtube.com/watch?v=eL-KzMXSXXI&list=PLDV1Zeh2NRsDGO4--qE8yH72HFL1Km93P&index=16
2. https://www.youtube.com/watch?v=cIBFEhD77b4&list=PLDV1Zeh2NRsDGO4--qE8yH72HFL1Km93P&index=17

*/
