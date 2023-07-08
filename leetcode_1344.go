/*
URL := https://leetcode.com/problems/angle-between-hands-of-a-clock/
1344. Angle Between Hands of a Clock
GAAAH float64 precision errors coming up too
*/
func angleClock(hour int, minutes int) float64 {
    myAngle := float64(0.0)
    minuteAngle := solveMinuteAngle(minutes)
    minutesPassedFraction := float64(minutes)/float64(60)
    hourAngle := solveHourAngle(hour, minutesPassedFraction)
    // Fix one of the angles ( e.g the minute angle in this case )
    // Can go CW or CCW from the minute Angle to the hour Angle here
    // problem of overlapping cases though
    // or take it a min angle, max angle idea?
    minAngle := math.Min(minuteAngle, hourAngle)
    maxAngle := math.Max(minuteAngle, hourAngle)
    measureOne := math.Abs((float64(360.0) - maxAngle) + minAngle)
    measureTwo := math.Abs(maxAngle - minAngle)
    myAngle = math.Min(measureOne,measureTwo)
    return myAngle
}

func solveMinuteAngle(minute int) float64 {
    minAngle := float64(0)
    // numberDegreesInCircle := float64(360.0)
    minAngle = ((float64)(minute) * float64(6.0) )
    return math.Abs(minAngle)
}

func solveHourAngle(hour int, minutesPassedFraction float64) float64 {
    // Use as offset from high noon
    hour = hour % 12
    hourAngle := float64(0)
    // numberDegreesInCircle := float64(360.0)
    hourAngle = (((float64)(hour) + minutesPassedFraction) * float64(30.0) )
    return math.Abs(hourAngle)
}
