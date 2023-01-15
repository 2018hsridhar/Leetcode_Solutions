# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
'''
URL = https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
1457. Pseudo-Palindromic Paths in a Binary Tree

Node vals from 1->9 : 10 keys per node @ max w/10 freqeuncy values.
std::move(...) would be dope here.
Pass same hashmap over the tree.

Complexity
Let N := #-nodes in the btree
Let H := height of the btree : log_2(N) balanced and (N) skewed
Time = O(N)
Space = O(H) ( IMP ) O(1) ( EXP ) 

21 mins
'''
class Solution:
    
    def unitDigFreqMapisPalindromic(self,unitDigitFreqMap:dict) -> bool:
        hitOdd = False
        isPseudoPalin = True
        # Must apply unpack func for non-iterable objects
        # enumerate(dict) != dict.items()
        for digit,digitFreq in unitDigitFreqMap.items():
            if(digitFreq % 2 == 1 and hitOdd == False):
                hitOdd = True
            elif(digitFreq % 2 == 1 and hitOdd == True):
                isPseudoPalin = False
                break
        return isPseudoPalin

    def isLeafNode(self,root:TreeNode) -> bool:
        return (root.left is None and root.right is None)

    def pseudoPalindromicPathsInternal(self,root: TreeNode, unitDigitFreqMap:dict) -> int:
        myNumberOfPaths = 0
        # Add here, for leaf node testing.
        unitDigitFreqMap[root.val] += 1
        if(Solution.isLeafNode(self,root)):
            if(Solution.unitDigFreqMapisPalindromic(self,unitDigitFreqMap)):
                myNumberOfPaths += 1
        else:
            if(root.left is not None):
                myNumberOfPaths += Solution.pseudoPalindromicPathsInternal(self,root.left, unitDigitFreqMap)
            if(root.right is not None):
                myNumberOfPaths += Solution.pseudoPalindromicPathsInternal(self,root.right, unitDigitFreqMap)
        unitDigitFreqMap[root.val] -= 1
        return myNumberOfPaths

    def pseudoPalindromicPaths (self, root: Optional[TreeNode]) -> int:
        if(root is None):
            return 0
        # TBH, you can preinitizile ( as an optimization )
        unitDigitFreqMap = dict()
        for val in range(1,10,1):
            unitDigitFreqMap[val] = 0
        return Solution.pseudoPalindromicPathsInternal(self,root,unitDigitFreqMap)
