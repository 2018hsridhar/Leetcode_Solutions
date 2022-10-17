/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    Node* findRoot(vector<Node*> tree) {
        Node* root = nullptr;
        map<Node*, Node*> parentMap;
        for(Node* parent : tree){
            if(parentMap.find(parent) == parentMap.end()){
                parentMap[parent] = nullptr;
            }
            for(Node* child : parent->children){
                parentMap[child] = parent;
            }
        }
        // structured binding / decomposition declaration
        // for(const auto& [key,val] entry : parentMap){
        for (const auto & [k,v] : parentMap ) {
            if(v == nullptr){
                root = k;
                break;
            }
        }
        return root;
    }
};
