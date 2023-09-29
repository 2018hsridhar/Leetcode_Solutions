/**
 * Definition for Employee.
 * type Employee struct {
 *     Id int
 *     Importance int
 *     Subordinates []int
 * }
 */

 /*
URL := https://leetcode.com/problems/employee-importance/
690. Employee Importance

Given a list of children here -> apply the algo recursively on the children
Can't we memoize things though?
Hmmm?
NO good notion of a root node though
We don't know who root node is\
Connect the edges as we go?

But we have the id - of the root - that we care about - and that matters

Complexity
Let E := #-employees
Time := O(E)
Space := O(E) ( EXP ) O(1) ( IMP ) 

DAG property still ensures summations will work as expected though
Process as list -> not recursive property. Needs conversion to tree structure
- can we avoid tree structure conversions here?
- no negative/positive value hacking gaaah

20 minutes
Accepted

 */

func getImportance(employees []*Employee, id int) int {
    adjMap := make(map[int][]int)
    empMap := make(map[int]int)
    fillEmpMap(employees, empMap)
    fillAdjMap(employees,adjMap)
    return getImportanceSum(id, empMap, adjMap)
}

func fillEmpMap(employees []*Employee, empMap map[int]int) {
    for _, employee := range employees {
        myId := employee.Id
        if _, ok := empMap[myId]; !ok {
            empMap[myId] = employee.Importance
        }
    }
}

func fillAdjMap(employees []*Employee, adjMap map[int][]int) {
    for _, employee := range employees {
        myId := employee.Id
        if _, ok := adjMap[myId]; !ok {
            adjMap[myId] = make([]int, len(employee.Subordinates))
        }
        copy(adjMap[myId], employee.Subordinates)
    }
}

func getImportanceSum(id int, empMap map[int]int, adjMap map[int][]int) int {
    curSum := empMap[id]
    subords := adjMap[id]
    for _, subord := range subords {
        curSum += getImportanceSum(subord, empMap,adjMap)
    }
    return curSum
}
