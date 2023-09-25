/*
Initial thinking : leverage maps of <int,set<int>> here
URL := https://leetcode.com/problems/count-lattice-points-inside-a-circle/
2249. Count Lattice Points Inside a Circle
Each entry - x,y,r - are all radius points
binary search methods?
iterate and go thru as if a simulation too?
200 circles max
start the checks at diagonals (2,2) -> (2,3) -> (2,4) and so on? 
BFS/DFS on point space until you hit out the radius -> and check what is in the maps or not?
    -> this will converge at some point too ( see radius ) 



*/
func countLatticePoints(circles [][]int) int {
    latticeCount := 0
    // no sets : map of map type of thing going on in GoLang gaaah
    // correct but TLE gaaah
    // could be in the local->nonlocal map operation?
    pointsHit := map[int]map[int]bool{}
    for _, circle := range circles {
        fillPointMap(circle, pointsHit)
    }
    for _, yOfPoints := range pointsHit {
        latticeCount += len(yOfPoints)
    }
    return latticeCount
}

func fillPointMap(circle []int, pointsHit map[int]map[int]bool) {
    pointHitMapLocal := map[int]map[int]bool{}
    steps := [][]int{
        []int{-1,-1},
        []int{-1,0},
        []int{-1,1},
        []int{0,-1},
        []int{0,1},
        []int{1,-1},
        []int{1,0},
        []int{1,1},
    }
    queue := [][]int{}
    r := circle[2]
    centerPoint := []int{circle[0], circle[1]} 
    queue = append(queue, centerPoint)
    for(len(queue) > 0) {
        curPoint := queue[0]
        queue = queue[1:] // reslice operation ( would desire efficeincy at least ) but whatever
        x := curPoint[0]
        y := curPoint[1]
        // possible early terminate here? Need to watch out!
        if(pointHitMapLocal[x][y] == true) {
            continue
        }
        distToCenter := dist(curPoint, centerPoint)
        // expecting { after if clause (
        if(distToCenter <= (float64)(r)) {
            if _, ok := pointHitMapLocal[x]; !ok {
                pointHitMapLocal[x] = map[int]bool{}
            }
            pointHitMapLocal[x][y] = true
            for _, step := range steps {
                nextPoint := []int{curPoint[0] + step[0], curPoint[1] + step[1]}
                queue = append(queue, nextPoint) 
            }
        }
    }
    for k, localMap := range pointHitMapLocal {
        if _, ok := pointsHit[k]; !ok {
            pointsHit[k] =  map[int]bool{}
        }
        for v, _ := range localMap {
            pointsHit[k][v] = true
        }
    }
}

func dist(pointOne []int, pointTwo []int) float64 {
    xDist := (float64)(pointOne[0]) - (float64)(pointTwo[0])
    yDist := (float64)(pointOne[1]) - (float64)(pointTwo[1])
    dist := math.Sqrt(xDist*xDist + yDist*yDist)
    return dist
}
