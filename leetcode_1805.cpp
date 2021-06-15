/*

Can feel a bit more confusing to do in c++, for sure!

1805. Number of Different Integers in a String
URL = https://leetcode.com/problems/number-of-different-integers-in-a-string/

Sorting input would not help out here in any means


Big-O Complexity : ideal [T,S] = [O(N), O(1)] where N := number of elemens in string
Do not count an integer if already seen -> need to store in a visited set too

First, replace each non-digit character with a space
Then parse out your string



*/
#include <algorithm>
#include <iostream>
#include <string>
#include <regex>
#include <iterator>

class Solution {
public:
    int numDifferentIntegers(string word) 
    {
        int numDiffInts = 0;
        // std::replace(word.begin(), word.end(), '\w', ' ');
        
        std::regex e ("[a-z]");

        // std::string result;
        // std::regex_replace(word.begin(), word.end(), e, "$2");
        // std::cout << result;

        std::printf("String = %s\n", word.c_str());
        return numDiffInts;
    }
};
