// https://leetcode.com/problems/validate-binary-search-tree/#/description

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
    bool isValidBST(TreeNode* root) {
        return isValidBST_helper(root, LONG_MIN, LONG_MAX);
    }
    
    bool isValidBST_helper(TreeNode* root, long min, long max)
    {
        if(root == NULL) return true;
        else if ( root->val <= min || root->val >= max) return false;
        else return isValidBST_helper(root->left, min, root->val) && isValidBST_helper(root->right, root->val,max); 
    }
};
