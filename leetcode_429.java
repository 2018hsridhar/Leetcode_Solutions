// https://leetcode.com/problems/n-ary-tree-level-order-traversal

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) 
    {
        List<List<Integer>> traversal = new ArrayList<List<Integer>>();
        if(root == null) return traversal;
        List<Node> candidates = new ArrayList<Node>();
        candidates.add(root);
        traverse(traversal, candidates);
        return traversal;
    }
    
    public void traverse(List<List<Integer>> traversal, List<Node> candidates)
    {
        if(candidates.size() == 0)
            return;
        
        List<Integer> curLevel = new LinkedList<Integer>();
        for(Node n : candidates)
            curLevel.add(n.val);
        traversal.add(curLevel);
        
        List<Node> nextLevel = new ArrayList<Node>();
        for(Node can : candidates)
            nextLevel.addAll(can.children);
        traverse(traversal, nextLevel);
    }
}
```
