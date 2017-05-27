// https://leetcode.com/problems/binary-tree-tilt/#/description

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

    // get a tree's specific tilt
    int findTilt(TreeNode* root) 
    {
        if(root == NULL) 
            return 0;
        return nodalTilt(root) + findTilt(root->left) + findTilt(root->right);
    }
    
    // get a node's specific tilt
    int nodalTilt(TreeNode* root)
    {
        if(root == NULL)
            return 0;
        else if ( root->left == NULL && root->right == NULL)
            return 0; 
        return std::abs(sum(root->left) - sum(root->right));
    }
    
    // CAPTURE sum of node's of a given tree
    int sum(TreeNode* root)
    {
        if(root == NULL) 
            return 0;
        else if (root->left == NULL && root->right == NULL)
            return root->val;
        return root->val + sum(root->left) + sum(root->right);
    }
    
};
