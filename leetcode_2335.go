/*
URL := https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/
2335. Minimum Amount of Time to Fill Cups

*/
import "container/heap"

// https://pkg.go.dev/container/heap Copy all this boilerplate :-) 
// An IntHeap is a min-heap of ints.
type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] > h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x any) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

// Hard to tell in swap operation :-(
func fillCups(amount []int) int {
			numOps := 0
			h := &IntHeap{}
			heap.Init(h)
			for i := 0; i < len(amount); i++ {
				heap.Push(h,(amount[i]))
			}
			for {
				// Operator def on interface or not?
				topEl := (heap.Pop(h)).(int)
				secondEl := (heap.Pop(h)).(int)
				if topEl > 0  && secondEl > 0 {
					heap.Push(h,topEl-1)
					heap.Push(h,secondEl-1)
					numOps++
				} else if topEl > 0 && secondEl == 0 {
					heap.Push(h,topEl-1)
					heap.Push(h,secondEl) // push back dude!@
					numOps++
				} else {
					break // here, but not out of the infinite for loop?
				}
			}
			// Mem corruption happening?
			
	    return numOps
}
