'''
1154. Day of the Year
URL = https://leetcode.com/problems/day-of-the-year/description/
'''
def checkIfIsLeapYear(year: int) -> int:
    stat = False
    if(year % 4 == 0):
        if(year % 100 == 0):
            if(year % 400 == 0):
                stat = True
            else:
                stat = False
        else:
            stat = True
    return stat


class Solution:
    def dayOfYear(self, date: str) -> int:
        # https://stackoverflow.com/questions/10769630/initialize-python-dictionary-containing-a-strings-and-lists#:~:text=initialize%20python%20dictionary%20containing%20a%20strings%20and%20lists,item%3F%20dict%20%3D%20%5B%28%22string1%22%2C%20%5B1%2C2%5D%29%2C%20%28%22string2%22%2C%20%5B5%2C6%5D%29%2C..%5D%20So%3A
        monthDaysMap = {
            1:31,
            2:28,
            3:31,
            4:30,
            5:31,
            6:30,
            7:31,
            8:31,
            9:30,
            10:31,
            11:30,
            12:31
        }
        dayNum = 0
        tokens = re.split('-', date)
        year = int(tokens[0])
        month = int(tokens[1])
        days = int(tokens[2])
        # Ironically, port over code from elsewhere :-) ( https://leetcode.com/problems/number-of-days-between-two-dates/ ) 
        isLeapYear = checkIfIsLeapYear(year)
        if(isLeapYear and (month >= 3)):
            dayNum += 1
        numDaysTillMonth = 0
        for k,v in monthDaysMap.items():
            if(k < month):
                numDaysTillMonth += v
        dayNum += numDaysTillMonth
        dayNum += days
        return dayNum
