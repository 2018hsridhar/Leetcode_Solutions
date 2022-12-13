'''
URL = https://leetcode.com/problems/maximum-population-year/
1854. Maximum Population Year
Brute force solution works :-)
'''
class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        populationFreq = dict()
        for year in range(1950,2051,1):
            populationFreq[year] = 0
        for log in logs:
            birthYear = log[0]
            deathYear = log[1]
            for year in range(birthYear,deathYear,1):
                populationFreq[year] += 1
        maxPopulationYear = -1
        maxPopulationEncountered = 0
        for year in range(1950,2051,1):
            if(populationFreq[year] > maxPopulationEncountered):
                maxPopulationEncountered = populationFreq[year]
                maxPopulationYear = year
        return maxPopulationYear

