class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        """
        :type nums: List[int]
        :rtype: int
        """
        '''
        Meddling with python dictionaries
        https://www.youtube.com/watch?v=daefaLgNkw0
        Ditionaries = associative arrays : 2 linked values
        
        
        487. Max Consecutive Ones II
        https://leetcode.com/problems/max-consecutive-ones-ii/

        Strategy : BUDP
        Input never empty or null
        Let N := len(nums) ( boudned by 10,000 : not too large for POLY-time algos ) 
        Time = O(N)
        Space = O(N) ( exp ) O(1) ( imp ) 
        What if infinite stream case for RAM ( huge file/disc ) handling? How to solve such algos with little space or time -> O(1) desireable

        TEST CASES :
        (A) [0,0,0,0,0,0,0,0] => 0
        (B) [1,0,1,1,0] => 4
        (C) [1,1,1,1,1,1,1] => 7
        (D) [1,0,1,0,1,0,1,0] => 3
        (E)

        Constraint : Can flip only ONE zero ( may not flip any ) 
        Flipping a zero in between a seq of 1's always helps
        
        With [0], you check if either side equals [1]
        With [1], you check if either side equals [0]

        Accepted with most cases :-)
        
        '''
#         # student = {} # Start a dictionary
#         # ANY immutable data type
#         # Notice when key values updated as well!
#         student = {'name':'John','age':25,'courses':['Math','CompSci']}
#         # student['phone'] = '555-555'
#         # student['name'] = 'Jane'
#         # Pass in a dict tuple for updating as well
#         # student.update({'name':'Jane', 'age':26, 'phone':'555-5555'})
#         # del student['age']
#         # age = student.pop('age')
#         # print(age)
#         # print(student)
#         # print(student['phone']) # key access
#         # print(student.get('phone','Not Found')) # The get method tends to be safer : returns a default/None
        
#         # print(len(student))
#         # Candidates should be able to code up methods in a hashmap class to return keys, values, and pairings/items
#         # print(student.items())
#         for key, value in student.items():
#             print(key, value)

        n = len(nums)
        onesToLeft = [0 for i in range(n)]
        onesToRight = [0 for i in range(n)] # val for elem in range(num) syntax
        leftCount = 0
        rightCount = 0
        
        # Initialize left count
        for i in range(0,n,1):
            onesToLeft[i] = leftCount
            if(nums[i] == 1):
                leftCount += 1
            else:
                leftCount = 0
                
        # Initialize right count
        for i in range(n-1,-1,-1):
            onesToRight[i] = rightCount
            if(nums[i] == 1):
                rightCount += 1
            else:
                rightCount = 0
                
        # print(onesToLeft)
        # print(onesToRight)
            
        longestOnesSeq = 0
        # Iterate over each elem, and accum min totals
        # Reference before assignment runtime exception
        for i in range(0,n,1):
            curSeqLen = 1
            if(nums[i] == 1):
                curSeqLen = onesToLeft[i] + onesToRight[i]
                curSeqLen += 1
                if(i-1 >= 0 and nums[i-1] == 0):
                    curSeqLen = max(curSeqLen, 1 + curSeqLen + onesToLeft[i-1])
                elif(i+1 < n and nums[i+1] == 0):
                    curSeqLen = max(curSeqLen, 1 + curSeqLen + onesToRight[i+1])
            elif(nums[i] == 0):
                # Defacto toggle
                if(i-1 >= 0 and nums[i-1] == 1 and i+1 < n and nums[i+1] == 1):
                    curSeqLen = max(curSeqLen, 1 + onesToLeft[i] + onesToRight[i])
                elif(i-1 >= 0 and nums[i-1] == 1):
                    curSeqLen = max(curSeqLen, 1 + onesToLeft[i])
                elif(i+1 < n and nums[i+1] == 1):
                    curSeqLen = max(curSeqLen, 1 + onesToRight[i])
                    
            longestOnesSeq = max(longestOnesSeq, curSeqLen)
            
            
            
        return longestOnesSeq
            
            
            
