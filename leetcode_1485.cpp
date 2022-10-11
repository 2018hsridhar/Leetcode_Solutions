/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *random;
 *     Node() : val(0), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x) : val(x), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x, Node *left, Node *right, Node *random) : val(x), left(left), right(right), random(random) {}
 * };
 */
/*
NodeCopy class = clone of Node class
Analogous attributes and constructors.
Number of nodes up to a power of 10^3 only.
Node values within unsigned positive integers below 1 MIL.

Note : not guaranteed to be given the root of the tree -> but guaranteed a random pointer and some node in the tree. So explore all 3 options : left, right, and random.
Or perhaps we are guaranteed some type of root.
Easiest : return the corresponding <NodeCopy*> to <Node*> here.

1485. Clone Binary Tree With Random Pointer
URL = https://leetcode.com/problems/clone-binary-tree-with-random-pointer/


*/
class Solution {
public:
    NodeCopy* copyRandomBinaryTree(Node* root) {
        if(root == nullptr)
            return nullptr;
        // can have a node created in hashmap, but, data still needs to be set.
        // c++ errs out if it thinks you lack a template too.
        std::map<const Node*,NodeCopy*> copiedNodes;  // default : keys are const-qualified too.
        // Indicates that a node - and all corresponding data members - has been set.
        std::set<const Node*> visited;
        // BFS explore
        std::queue<Node*> toExplore;
        toExplore.emplace(root);
        while(toExplore.size() > 0){
            Node* parent = toExplore.front();
            toExplore.pop();
            if(visited.find(parent) != visited.end()) // processed node already.
            {
                continue;
            }
            // NOT found
            if(copiedNodes.find(parent) == copiedNodes.end()){
                copiedNodes[parent] = new NodeCopy(parent->val);
            }
            NodeCopy* parentCopy = copiedNodes[parent];
            visited.insert(parent);
            if(parent->left != NULL){
                if(copiedNodes.find(parent->left) == copiedNodes.end()){
                    copiedNodes[parent->left] = new NodeCopy(parent->left->val);
                }
                NodeCopy* leftCopy = copiedNodes[parent->left];
                parentCopy->left = leftCopy;
                toExplore.emplace(parent->left);
            }
            if(parent->right != NULL){
                if(copiedNodes.find(parent->right) == copiedNodes.end()){
                    copiedNodes[parent->right] = new NodeCopy(parent->right->val);
                }
                NodeCopy* rightCopy = copiedNodes[parent->right];
                parentCopy->right = rightCopy;
                toExplore.emplace(parent->right);
            }
            if(parent->random != NULL){
                if(copiedNodes.find(parent->random) == copiedNodes.end()){
                    copiedNodes[parent->random] = new NodeCopy(parent->random->val);
                }
                NodeCopy* randomCopy = copiedNodes[parent->random];
                parentCopy->random = randomCopy;
                toExplore.emplace(parent->random);
            }
        }
        return copiedNodes[root];
        // Never return stack mem addresses / addrs of local vars!
        // If you return mem from a func -> it will be heap-alloced memory `de facto`
    }
    
};
