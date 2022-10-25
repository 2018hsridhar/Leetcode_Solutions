/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

/*
Challenge : we already know level-order traversal well
Can we leverage DFS algos instead, knowing the problem constraints?

1660. Correct a Binary Tree
URL = https://leetcode.com/problems/correct-a-binary-tree/

SPACE = O(N) ( exp ) ( impl ) 
TIME = O(N)

14 mins to solution :-)

*/

class Solution {
    
private:
    void dfs(map<TreeNode*,TreeNode*> &mp, TreeNode* parent){
        TreeNode* leftChild = parent->left;
        TreeNode* rightChild = parent->right;
        if(leftChild != nullptr){
            if(mp.find(leftChild) == mp.end()){
                mp[leftChild] = parent;
            } else {
                cutBadParentNode(mp,leftChild);
                return;
            }
            dfs(mp,leftChild);
        }
        if(rightChild != nullptr){            
            if(mp.find(rightChild) == mp.end()){
                mp[rightChild] = parent;
            } else {
                cutBadParentNode(mp,rightChild);
                return;
            }
            dfs(mp,rightChild);            
        }
        return;
    }
    
    void cutBadParentNode(map<TreeNode*,TreeNode*>& mp, TreeNode* wrongChildNode){
        // printf("Here bad node = %d\n", wrongChildNode->val);
        TreeNode* parent = mp[wrongChildNode];
        TreeNode* grandparent = mp[parent];
        if(grandparent->left == parent){
            grandparent->left = nullptr;
        } 
        if(grandparent->right == parent){
            grandparent->right = NULL;
        }
        return;
    }
    
public:
    // it is never the first level too
    TreeNode* correctBinaryTree(TreeNode* root) {
        map<TreeNode*,TreeNode*> mp;
        mp[root] = NULL; // the special case
        dfs(mp,root);
        return root;
    }
};
