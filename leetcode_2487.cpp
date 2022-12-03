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
 /*
 2487. Remove Nodes From Linked List
URL = https://leetcode.com/problems/remove-nodes-from-linked-list/

Use a stack here.
Complexity
Let N := len(head)
Time = O(N)
Space = O(N) ( EXP ) O(1) (IMP ) 
 */
class Solution {
public:
    ListNode* removeNodes(ListNode* head) {
        std::stack<ListNode*> stk;
        while(head != nullptr){
            while(stk.size() > 0){
                ListNode* curTopNode = stk.top();
                if(curTopNode->val < head->val){
                    stk.pop();
                } else {
                    break;
                }
            }
            // push only if it can be pushed here
            stk.push(head);
            head = head->next;
        }
        // have a stack here
        ListNode* curHead = nullptr;
        while(stk.size() > 0){
            ListNode* newHead = stk.top();
            newHead->next = curHead;
            curHead = newHead;
            stk.pop();
        }      
        return curHead;
    }
};
