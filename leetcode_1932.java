/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*

URL = https://leetcode.com/problems/merge-bsts-to-create-single-bst/ 
1932. Merge BSTs to Create Single BST

Let us leverage the invariant that node values in the node's LST have value STRICTLY LESS than the root's value
and nodes in the right subtree have value STRICTLY MORE than node's value

Note : we may be able to form 

HINT : leverage a hashmap
    -> store the min values and max values of BSTs ( seen in other problems ) 
    
COMPLEXITY
Time = ___
Space = ___

TEST CASES
(A)
(B)
(C)
(D)
(E)

Also this is a list, so deletion may or may not be possible. But a hashmap conversion also entails alacrity as well
No two roots have same value -> we are in luck here too!
Return null if a root is not able to be ascertained as well.
Count number of trees too ( hey you queue root ste handles this ! ) 


Remember that we must maintain the count of operatinos here too

Strategies : Hashtable, Queue, BFS ( Fringe-esque manner ) or DFS ( fringe-esque manner )
            I am unsure how binary search could help here though?

*/
class Solution 
{
    
    public class Wrapper
    {
        public TreeNode node;
        public int min;
        public int max;
        
        public Wrapper()
        {
            this.node = null;
            min = Integer.MIN_VALUE;
            max = Integer.MAX_VALUE;
        }
        
        // The "this" keyword is really a self-referential pointer in the hiding
        public Wrapper(TreeNode node, int min, int ma)
        {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }
    
    public TreeNode canMerge(List<TreeNode> trees) 
    {
        TreeNode newRoot = null;
        List<Wrapper> fringe = new ArrayList<Wrapper>();
        Queue<TreeNode> toExplore = new LinkedList<TreeNode>();  // abstract class needs a concrete class instantiation on the RHS
        HashMap<TreeNode, Wrapper> rangeInfo = new HashMap<TreeNode, Wrapper>();
        Set<TreeNode> rootSet = new HashSet<TreeNode>();
        
        if(trees == null || trees.size() == 0)
        {
            return null;
        }
        int n = trees.size();
        
        // [1] Initialize the HASHMAP with initialize range info for all rooted nodes
        // [2] In same for loop, add the nodes to the queue as well
        for(int i = 0; i < n; ++i)
        {
            TreeNode cur = trees.get(i);
            Wrapper metadata = new Wrapper();
            if(cur != null && !rangeInfo.containsKey(cur))
            {
                metadata.node = cur;
                if(cur.left != null)
                {
                    metadata.min = cur.left.val;
                }
                if(cur.right != null)
                {
                    metadata.max = cur.right.val;
                }
                rangeInfo.put(cur, metadata);
            }
            toExplore.add(cur);
        }
        
        // [3] Initialize the fringe and first root node, along with the root set
        newRoot = trees.get(0);
        rootSet.add(newRoot);
        if(newRoot.left != null)
            fringe.add(rangeInfo.get(newRoot.left));
        if(newRoot.right != null)
            fringe.add(rangeInfo.get(newRoot.right));
        
        // [4] The meat of our algorithm here
//        List<Wrapper> fringe = new ArrayList<Wrapper>();
//         Queue<TreeNode> toExplore = new LinkedList<TreeNode>();  // abstract class needs a concrete class instantiation on the RHS
//         HashMap<TreeNode, Wrapper> rangeInfo = new HashMap<TreeNode, Wrapper>();
//         Set<TreeNode> rootSet = new HashSet<TreeNode>();
        
        // Handle null pointer exception later
        while(!toExplore.isEmpty())
        {
            // Incorporate terminating condition into loop logic
            if(toExplore.size() == rootSet.size())
            {
                break;    
            }
            while(!fringe.isEmpty())
            {
                Wrapper candidate = fringe.remove(0);
                // If either children are null : well hey, they are infinites too. Leverage that as well.
                TreeNode testNode = candidate.node; 
                TreeNode lst = testNode.left;
                TreeNode rst = testNode.right;
                int testLeft = candidate.min;
                int testMax = candidate.max;
                if(rangeInfo.containsKey(testNode))
                {
                    // Now perform range tests , as an appends is a possibility here
                    // remember this : the append in itself is just the hashmaps left nad right too : why not just port that over?
                    
                    // provided append tests pass : if not, return null root ( early termination ) 
                    TreeNode parent = testNode;
                    parent.left = testNode.left;
                    parent.right = testNode.right;
                    rangeInfo.remove(testNode); // we kick this mini BST out cuz it was concatenated here
                    
                    // Add the new fringe nodes now
                    if(lst != null) fringe.add(rangeInfo.get(lst));
                    if(rst != null) fringe.add(rangeInfo.get(rst));
                    
                    // Go update the root node's range information now
                    // Note : aways update according to the fringe valies anyways : as the depths represent a property too!
                    
                    // Check if we need a new root node now
                    if(fringe.isEmpty())
                    {
                        break;
                    }
                }
            }
            
            // Grow a seperate subtree, as the root set is empty here
            if(fringe.isEmpty())
            {
                if(!rootSet.contains(newRoot))
                    rootSet.add(newRoot);
                newRoot = toExplore.poll(); 
            }
        }
        
        if(rootSet.size() != 1)
            return null;
        
        return newRoot;
    }
}

