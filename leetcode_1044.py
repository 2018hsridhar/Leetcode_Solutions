'''

1044. Longest Duplicate Substring
https://leetcode.com/problems/longest-duplicate-substring/

Occurences may overlap, but analyze duplicates
E.g. 'anana' => 'ana' (0,2) and (2,4)
Lowercase English letters only
Length up to 3000, from 2, here

COMPLEXITY
Let N := len(str)
TIME = O()
SPACE = O() ( CALL STK ) O() ( AUX )

-> do test construction!

TEST CASES :
(A) "aa"    => 'a'
(B) "aaaa"
    => "aaa"
(C)  "abcdefghijklmnopqrstuvwxyz"
    => ""
(D) "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" => YEP
(E)
(F) "wetgerhwerqwerwetgerhwerqwereqwgqerbqewrgbwwetgerhwerqwereqwgqerbqewrgbwwetgerhwerqwereqwgqerbqewrgbwwetgerhwerqwereqwgqerbqewrgbwwetgerhwerqwereqwgqerbqewrgbweqwgqerbqewrgbw"
    => PASSES

Suffix Trie solution : construct suffix trie data structure
How to code up data structures in python though?


'''
    
from collections import defaultdict # Generic collections library
from ctypes import c_int, addressof

class Solution:
    def longestDupSubstring(self, s: str) -> str:
        #  Note : you must get the substring as well : could store word in each node object too
        # of leverage string concatenation in param passing?
        # print("Node docstring = %s\n" % Solution.Node.__doc__)
        # temp = dict()
        # expr = 'b' in temp
        # print(expr)
        longestStr = ""  
        root = Solution.SuffixTree()
        for i in range(len(s)-1,-1,-1):
            suffix = s[i:]
            subProblemStr = root.insert(suffix)
            if len(subProblemStr) > len(longestStr):
                longestStr = subProblemStr
        return longestStr
    
# https://www.tutorialspoint.com/python_data_structure/python_linked_lists.htm
# https://www.youtube.com/watch?v=wfcWRAxRVBA

    class SuffixTree:
        '''
        Class SuffixTree
        Provides Utility Functions
        '''
        def __init__(self):
            self.root = Solution.Node()
        
        # insert a string here
        def insert(self, word):
            maxStr = ""
            inSuffixTrie = True
            curr = ""
            depth = 0
            currNode = self.root # always start at the root node anyways here
            for letter in word:
                # print(id(currNode)) # Clearly, ID = addresses differ substantially
                if letter in currNode.children: # if key in dict
                    currNode = currNode.children[letter]
                    if inSuffixTrie == True:
                        curr = curr + currNode.letter   # while we are in though ( note this check ! )
                        if len(curr) > len(maxStr) :
                            maxStr = curr
                    currNode.depth = depth
                    depth += 1
                else:
                    inSuffixTrie = False
                    depth += 1
                    newChild = Solution.Node(letter,depth,dict())
                    currNode.children[letter] = newChild
                    currNode = newChild # Is Py pointer reassignment failing here?
            return maxStr


    # Nested classes : access modifier pattern        
    # Enjoy the <self> referential pointer
    # Is not JAVA -> no multiple constructors
    # Default value strictness
    class Node:
        '''
        Class Node
        '''
        letter = ''
        depth = 0
        children = dict()
        
        # How to support multiple constructors though?
        def __init__(self, letter='',depth=0,children=dict()):  # default values tends to safety too!
            self.letter = letter
            self.depth = depth
            self.children = children

#         def __init__(self,letter,depth):    # Initializer constructor 
#             self.letter = letter
#             self.depth = depth
        
        
        
        
