/*
Only 1K calls @ maximum
Events interval intersection strikes again

729. My Calendar I
URL := https://leetcode.com/problems/my-calendar-i/

Insert into a priority queue
Reset the priorioty queue for each call?
Any way to exec faster range querying though?

Complexity
Let N := #-calls
Time : O(N*X)
Space := O(N) ( EXP ) O(1) ( IMP ) 

Both non-priority qeueu and priority queue based approaches need a notion of start of each 
of the keys to be sorted. No good way to avoid this sort property need.

1K calls max -> can avoid over optimizing here.

*/
type MyCalendar struct {
    myEvents [][]int
}

func Constructor() MyCalendar {
    return MyCalendar {
        myEvents : [][]int{},
    }
}

// Did we even have to code up the sorted order logic GAAAH
// Seems extra work done anyways too! 
func (this *MyCalendar) Book(start int, end int) bool {
   // [1] check for intersection -> if intersect, return false
   hasIntersect := false
   for _, event := range this.myEvents {
       eStart := event[0]
       eEnd := event[1]
       if(eventsHaveOverLap(eStart,eEnd,start,end)){
           hasIntersect = true
           break
       }
   }
   if(hasIntersect == true) {
       return false
   }
   newBooking := []int{start,end}
   this.myEvents = append(this.myEvents, newBooking)
   return true
}

func eventsHaveOverLap(e1s, e1e, e2s, e2e int) bool {
    return (e2s <= e1s && e1s < e2e) || (e1s <= e2s && e2s < e1e)
}


/**
 * Your MyCalendar object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(start,end);
 */
