783. Minimum Distance Between BST Nodes
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    
    void buildList(TreeNode* root, std::vector<int> &vals) 
    {
        if(!root)
        {
            return;
        }
        else
        {
            vals.push_back(root->val);
            buildList(root->left, vals);
            buildList(root->right, vals);
        }
    }
    
    int minDiffInBST(TreeNode* root) 
    {
        // [1] set up an arraylist, of <Node> values
        std::vector<int> treeVals;
        buildList(root, treeVals);

        // [2] SORT the vector
        std::sort(treeVals.begin(), treeVals.end());
        
        // [3] GET min_diff [ set to max-min, tbh]
        int first = treeVals[0];
        int last = treeVals.back();
        int minDiff = last - first;
        for(int i = 0; i < treeVals.size() - 1; ++i)
        {
            int curr_diff = (treeVals[i+1] - treeVals[i]);
            if(curr_diff < minDiff)    
                minDiff = curr_diff;
        }
        return minDiff;
    }



    
};

// https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/

/*
LEARNINGS
If you have to solve, for a difference, in a problem, ask if it is an absolute difference or not.
A langauge, like C, does not provide certain dstructs. NEVER USE C for interview problems, unless you are working in OS-land
The size of the BST will be between 2 and 100. - a size constraint, oftentimes, let’s you know if you can get away with an easy, cop-out data structure. In this case, I was able to get away with an array.
But, if the size of this tree scaled, to say, 1,000,000 … I probably could not have
I believe, this is why in coding competitions, size constraints are usually mentioned. To let competitors know if they can get away with easy solutions that use space.
The array representation of 2-D[ and this can also scale to D-D] trees actually proves incredibly useful.
If you needed to convert, among the two structures, with ease, you can!
Oftentimes, when comparing 2/D nodes, in a tree, consider the following
Two nodes :: a specific tree-order traversal. You seldom compare 2 random nodes
D-d nodes :: a k-window sized priority queue/heap
Tree’s can usually be flattened, especially from bottom-up
This is common in recursive solutions
In this case, imagine what would happen if you processed all the leaf nodes, and eliminate the LAST level. What happens to the 2nd-to-last level, here? 
Hence, when drawing out trees, try bottom-up now, instead of always top-to-bottom, too? 
*/
