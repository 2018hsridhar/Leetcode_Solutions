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
 URL = https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 2095. Delete the Middle Node of a Linked List

Let N := len(head)
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Cases
(A) [1]
(B) [1,3]
(C) [1,3,4]
(D) [1,3,4,7]
(E) [1,3,4,7,1]
(F) [1,3,4,7,1,2]

8 min
 */
class Solution {
public:
    ListNode* deleteMiddle(ListNode* head) {
        ListNode* newHead = head;
        if(head->next == nullptr) {
            return nullptr;
        } else if ( head->next->next == nullptr){
            head->next = nullptr;
            return head;
        }
        int listLen = getListLen(head);
        int middlePos = listLen / 2;
        ListNode* prev = nullptr;
        ListNode* cur = head;
        // Be exactly less then middlePos
        for(int i = 0; i < middlePos; ++i){
            prev = cur;
            cur = cur->next;
        }
        prev->next = cur->next; // delete pointer in middle
        return newHead;
    }

private:
    int getListLen(ListNode* head){
        int len = 0;
        while(head != nullptr){
            len++;
            head = head->next;
        }
        return len;
    }

};
