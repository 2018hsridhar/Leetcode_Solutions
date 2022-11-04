/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
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
 109. Convert Sorted List to Binary Search Tree
URL = https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

Let N := len(SLL)
Let H := log_2(N)
TIME = O(N^2)
SPACE = O(H) ( IMP ) O(1) ( EXP ) 

Cases
(A) [0,1] => PASS
(B) [0,1,2,3] => PASS
(C) [1] => PASS
(D) [1,2,3,4,5] => PASS
(E) [1,2,3,4,5,6,7,8,9,10,11] => PASS
(F)

13 mins to solution :-)
GOT IT!

 */
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if(head == nullptr){
            return nullptr;
        }
        TreeNode* res = internal(head);
        return res;
    }

private:
    TreeNode* internal(ListNode* head){
        if(head == nullptr){
            return nullptr;
        } else if ( head->next == nullptr){ // handle singleton node case
            return new TreeNode(head->val);
        } else {
            // get to the middle and take it from there
            // guaranteed height balance to work from middle too!
            auto middleNodeInfo = getMiddleNode(head);
            TreeNode* subTreeRoot = new TreeNode(middleNodeInfo.second->val);
            ListNode* endLeftList = middleNodeInfo.first;
            endLeftList->next = nullptr;
            ListNode* startRightList = middleNodeInfo.second->next;
            subTreeRoot->left = internal(head);
            subTreeRoot->right = internal(startRightList);
            return subTreeRoot;
        }
    }

    pair<ListNode*,ListNode*> getMiddleNode(ListNode* head){
        bool hitNode = false;
        ListNode* slowPtr = head;
        ListNode* precPtr = head;
        ListNode* fastPtr = head;
        while(fastPtr != nullptr){
            fastPtr = fastPtr->next;
            if(fastPtr != nullptr){
                fastPtr = fastPtr->next;
            } else if ( fastPtr == nullptr){
                break;
            }
            slowPtr = slowPtr->next;
            if(hitNode){
                precPtr = precPtr->next;
            }
            hitNode = true;
        }
        return std::make_pair(precPtr,slowPtr);
    }

};
