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

IDEAL : [T,S] = [O(V+E),O(V)]
CURRENT : 

Base cases 

Edge Cases

Gotchas



*/
