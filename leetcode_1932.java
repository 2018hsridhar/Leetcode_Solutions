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

Node vals are known to be positives in range of [1,50000]
[[3],[3,1]] is luckily invalid anyways!

TEST CASES
(A)[[2,1],[3,2,5],[5,4]] 
(B) [[3],[4,3]]
    [4,3] = expected
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

You forgot the case where the root may not even possess two children nodes ( e.g. 2L1 )
In this case, we may get an overlap ( e.g. 2R3 )
This is still a valid operation, and the fringe may have involved the root here as well. Exert caution now!

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
            min = 0;
            max = 50001; // ma of 50,000
            // min = Integer.MIN_VALUE;
            // max = Integer.MAX_VALUE;
        }
        
        // The "this" keyword is really a self-referential pointer in the hiding
        public Wrapper(TreeNode node, int min, int max)
        {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }
    
    // Range Info hashmap should be based on integers ... NOT on node addresses ( can cause a trip up with fringe analysis : leaf(1) != root(1) case ) 
    public TreeNode canMerge(List<TreeNode> trees) 
    {
        TreeNode newRoot = null;
        List<Wrapper> fringe = new ArrayList<Wrapper>();
        Queue<TreeNode> toExplore = new LinkedList<TreeNode>();  // abstract class needs a concrete class instantiation on the RHS
        HashMap<Integer, Wrapper> rangeInfo = new HashMap<Integer, Wrapper>();
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
            if(cur != null && !rangeInfo.containsKey(cur.val))
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
                rangeInfo.put(cur.val, metadata);
            }
            toExplore.add(cur);
        }
        
        // [3] Initialize the fringe and first root node, along with the root set
        // Make sure the fringe itself is properly initialized here : we pass in these values later, depending on direction of add as well
        // Oh and make sure the root is part of the fringe set as well : hey we could get a bad case as well
        // But the root case begets much caution too ... as this fringe is not guaranteed its emptiness
        // One possibility : go fill that up, if possible , and then proceed with the legitimate fringe as expected
        // Wait we need not add the root to the fringe -> as the root trees differ, and a fringe must be a leaf
        // But what if the root HAS no leaves? 
        // Well that is a special case indeed. Is in itself a frige at this point
        
        
        newRoot = trees.get(0);
        rootSet.add(newRoot);
        if(newRoot.left == null && newRoot.right == null)
        {
            fringe.add(rangeInfo.get(newRoot.val));
        }
        if(newRoot.left != null)
        {
            fringe.add(new Wrapper(newRoot.left, 0, newRoot.val));
        }
        if(newRoot.right != null)
        {
            fringe.add(new Wrapper(newRoot.right, newRoot.val, 50001));
        }
        
        // [4] The meat of our algorithm here
//         List<Wrapper> fringe = new ArrayList<Wrapper>();
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
                // If either children are null : well hey, they are infinites too. Leverage that as well.
                Wrapper fringeWrapper = fringe.remove(0);
                TreeNode fringeNode = fringeWrapper.node; 
                if(fringeNode == newRoot)
                {
                    continue; // ignore this case
                }
                // Go remove a former root, if we end up adding it anyways!
                if(rootSet.contains(fringeNode))
                {
                    rootSet.remove(fringeNode);
                }
                int fringeMin = fringeWrapper.min;
                int fringeMax = fringeWrapper.max;
                if(rangeInfo.containsKey(fringeNode.val))
                {
                    // Now perform range tests , as an appends is a possibility here
                    // remember this : the append in itself is just the hashmaps left and right too : why not just port that over?
                    Wrapper connecteeMeta = rangeInfo.get(fringeNode.val);
                    TreeNode connectee = connecteeMeta.node;
                    int connecteeMin = connecteeMeta.min;
                    int connecteeMax = connecteeMeta.max;
                    
                    // Not sure about handling -INT_MIN, INT_MAX cases though. A bit lost there in the connection setup
                    // Handle as single child or dual child cases instead ( you incorporated flag values 0,50001 for this ) 
                    // wait a second .. on the fringe, you may have a case where it has a flag as well. Shit
                    // If not, early halt with a null root
                    if(connecteeMin == 0)
                    {
                        if(connecteeMax > fringeMax)
                        {
                            return null;
                        }
                    }
                    else if ( connecteeMax == 50001)
                    {
                        if(connecteeMin < fringeMin)
                        {
                            return null;
                        }
                    }
                    else
                    {
                         if (!( fringeMin < connecteeMin && connecteeMax < fringeMax ))
                         {
                            return null;
                         }
                    }
                                          

                    Wrapper fringeMeta = rangeInfo.get(fringeNode.val);
                    TreeNode lst = fringeNode.left;
                    TreeNode rst = fringeNode.right;    
                    fringeNode.left = connectee.left;
                    fringeNode.right = connectee.right;
                    rangeInfo.remove(fringeNode.val); // we kick this mini BST out cuz it was concatenated here
                    
                    // Add the new fringe nodes now
                    if(lst != null) 
                    {
                        Wrapper lstMeta = rangeInfo.get(lst.val);
                        lstMeta.min = 0;
                        lstMeta.max = fringeNode.val;
                        fringe.add(lstMeta);
                    }
                    if(rst != null)
                    {
                        Wrapper rstMeta = rangeInfo.get(rst.val);
                        rstMeta.min = fringeNode.val;
                        rstMeta.max = 50001;
                        fringe.add(rstMeta);
                    }
                    
                    // Go update the root node's range information now
                    // Note : aways update according to the fringe valies anyways : as the depths represent a property too!
                    // Oh but the root might also have a flag too. Be careful!
                    Wrapper rootMeta = rangeInfo.get(newRoot);
                    rootMeta.min = Math.min(rootMeta.min, fringeNode.val);
                    rootMeta.max = Math.max(rootMeta.max, fringeNode.val);
                    
                    // Check if we need a new root node now
                    if(fringe.isEmpty())
                    {
                        break;
                    }
                }
            }
            
            // Grow a seperate subtree, as the root set is empty here
            // If node in to explore has already been checked : go ahead and skip it.
            if(fringe.isEmpty())
            {
                if(!rootSet.contains(newRoot))
                {
                    rootSet.add(newRoot);
                }
                newRoot = toExplore.poll(); 
                if(!rangeInfo.containsKey(newRoot))
                {
                    continue; // proceed to rest of iteration
                }
                else
                {
                    rootSet.add(newRoot); 
                }
            }
        }
        
        if(rootSet.size() != 1)
            return null;
        return newRoot;
    }
}









