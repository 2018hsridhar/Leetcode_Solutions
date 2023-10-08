/*
URL := https://leetcode.com/problems/rectangle-area/
223. Rectangle Area

Know this from Uber interview from a long long time ago
Compelxity
Time := O(1)
Space := O(1) ( EXP & IMP ) 

Return total area covered
We currently have intersection
But total area is easy : subtract intersectionArea
*/
func computeArea(ax1 int, ay1 int, ax2 int, ay2 int, bx1 int, by1 int, bx2 int, by2 int) int {
    cx1 := max(ax1,bx1)
    cx2 := min(ax2,bx2)
    cy1 := max(ay1,by1)
    cy2 := min(ay2,by2)
    xDelta := abs(cx2 - cx1)
    yDelta := abs(cy1 - cy2)
    intersectionArea := xDelta * yDelta // if the intersection exists, that is :-) 
    areaOne := abs(ay1-ay2) * abs(ax1-ax2)
    areaTwo := abs(by1-by2) * abs(bx2-bx1)
    if(ax2 <= bx1 || ax1 >= bx2 || ay2 <= by1 || ay1 >= by2) {
        totalArea := areaOne + areaTwo
        return totalArea
    }
    totalArea := areaOne + areaTwo - intersectionArea
    return totalArea
}

func max(a, b int) int {
    if(a > b) {
        return a
    }
    return b
}

func abs(a int) int {
    if(a < 0) {
        return a * -1
    }
    return a
}

func min(a, b int) int {
    if(a < b) {
        return a
    }
    return b
}
