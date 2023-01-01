'''
820. Short Encoding of Words
URL = https://leetcode.com/problems/short-encoding-of-words/

Problem : Algorithmic, Suffix Trie, DFS/Counting

Complexity:
Let N := len(words)
Let K := MAX(len(words[i])) = 7 here
TIME = O(NK)
Space = O(NK) ( EXP ) O(1) ( IMP )

Case Testing :
(A) ["a","b","c","d","e"] => 10
(B) ["a","aa","aaa","aaaa","aaaaa"] => 6
(C) ["abc","def","ghi"] => 12
(D) ["abc","abc",'abc"] => 4
(E)

GAAAH 40 minutes to solve!

'''

class Node:
    children = dict()
    letter = '' # default val

    def __init__(self, letter:str) -> None:
        self.letter = letter # self=this, in python
        self.children = dict() # make sure to create this too during obj initialization!


def insertSuffixIntoTrie(suffixTrieRoot: Node, reversedWord:str) -> None:
    curNode = suffixTrieRoot
    # You want the delta counted, after initializedNewNode is set to TRUE!
    for letter in reversedWord:
        if(letter not in curNode.children):
            curNode.children[letter] = Node(letter)
        curNode = curNode.children[letter]
    # 0 out if the word was never inserted into the suffix trie

# Hang on : root needs special handling too!
# We need to know the number of leaf nodes! Take note of this too!
# Append (+1) if node is a leaf, to account for '#' symbol addition
# You are not frequencyu counting here GAAAH
def getSumOfLeafDepths(suffixTrieRoot: Node, curLen: int, ans: List[int]) -> None:
    myChildren = suffixTrieRoot.children
    if(len(myChildren) == 0): # leaf node case!
        ans[0] += (curLen + 1)
    else:
        for childLetter,childNode in myChildren.items():
            getSumOfLeafDepths(childNode,curLen + 1, ans)

class Solution:
    def minimumLengthEncoding(self, words: List[str]) -> int:
        suffixTrieRoot = Node('')
        for word in words:
            reversedWord = word[::-1]
            insertSuffixIntoTrie(suffixTrieRoot,reversedWord)
        ans = [0]
        getSumOfLeafDepths(suffixTrieRoot, 0, ans)
        myMinLengthEncoding = ans[0]
        return myMinLengthEncoding
