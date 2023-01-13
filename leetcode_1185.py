import datetime

class Solution:

    def numOfDays(self,date1, date2):
        return (date2-date1).days
     
    def dayOfTheWeek(self, day: int, month: int, year: int) -> str:
        # First date = 1971 ( Jan 01, 1971 ) 
        # What day does this fall on
        # WE can modulo by 7 here.
        # Account for leap calendar years too
        # Which years are leap years?
        # Dating system : 0 -> Sunday and 6 -> Saturday.
        baseDate = 0 # 'Friday' 
        yearBase = 1971
        monthBase = 0
        dayBase = 1
        # Can't w leverage language built ins isntead?
        # https://www.geeksforgeeks.org/python-program-to-find-number-of-days-between-two-given-dates/
        dateBase = date(1971,1,1)
        dateCur = date(year,month,day)
        dateDelta = Solution.numOfDays(self,dateBase, dateCur)
        dayOfWeekKey = dateDelta % 7
        dayOfWeekMap = {
            0: 'Friday',
            1: 'Saturday',
            2: 'Sunday',
            3: 'Monday',
            4: 'Tuesday',
            5: 'Wednesday',
            6: 'Thursday'
        }
        return dayOfWeekMap[dayOfWeekKey]




