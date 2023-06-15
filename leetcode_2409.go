/*
URL := https://leetcode.com/problems/count-days-spent-together/
2409. Count Days Spent Together

*/
func getDay(dateTime string ) int {
    daysPerMonth := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
    month, _ := strconv.Atoi(dateTime[0:2])
    day, _ := strconv.Atoi(dateTime[3:])
    myDay := day
    for i := 0; i < month - 1; i++ {
        myDay += daysPerMonth[i]
    }
    return myDay
}

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
    numDaysTog := 0
    aliceStart := getDay(arriveAlice)
    aliceEnd := getDay(leaveAlice)
    bobStart := getDay(arriveBob)
    bobEnd := getDay(leaveBob)
    // interval overlap here
    latestArrival := (int)(math.Max((float64)(aliceStart), (float64)(bobStart)))
    earliestDeparture := (int)(math.Min((float64)(aliceEnd), (float64)(bobEnd)))
    if earliestDeparture < latestArrival {
        numDaysTog = 0
    } else {
        numDaysTog = earliestDeparture - latestArrival + 1
    }
    return numDaysTog
}
