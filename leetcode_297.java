/*
THOUGHT PROCESS
- 297. Serialize and Deserialize Binary Tree
URL = https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
We know in the deserialization, that we have to create the same number of tree nodes we iterated over
We also know that the values in a Binary Tree may be duplicates, BUT, addresses are unique as well
Each node in a binary tree has {0,1,2} children. Leverage this ADT rep. invariant too.
The serialization must explore all nodes as well. 
Can we use a outer-scoped ID generator as well?
Serializations can include additional info ( harken back to run-length encodings )
Maybe nest a "()" within each tiny node struct?

CONSTRAINTS
Supremum(Num nodes) = 10,000
Infinum(Num_nodes) = 0
Node vals within [-1000,1000]




COMPLEXITY
Let N := #-nodes
Let H := height of the Binary Tree ( log_2(n) balanced, (n) skewed ) 
TIME = O()
SPACE = O()

TEST CASES
We encountered a MLE run-time exception earlier, BUT, what if we can reduce memory
(A) the bad skew case : 
    [1,null,2,null,3,null,4,null,5,null,6,null,7]
(B) [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
(C) [1]
(D)
(E)
(F)

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    HashMap<TreeNode, Integer> nodeIds;
    int idGen = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) 
    {
        String result;
        StringBuilder sb = new StringBuilder("");
        nodeIds = new HashMap<TreeNode,Integer>();
        nodeIds.put(root,idGen);
        ++idGen;
        dfs(root,nodeIds,sb);
        result = sb.toString();
        return result;
    }
    
    // The root has been added here as well ( single node case ) 
    // IDs of parents always generated before we continue traversal
    // but need to gen leftId, rightID here
    public void dfs(TreeNode root, HashMap<TreeNode,Integer> nodeIds, StringBuilder sb)
    {
        if(root == null)
        {
            return;
        }
        // Check if to create the new pairing (node,ID) here
        else
        {
            int leftId = -1;
            int rightId = -1;
            if(root.left != null)
            {
                nodeIds.put(root.left,idGen);
                leftId = idGen;
                idGen++;
                dfs(root.left,nodeIds, sb);
            }
            if(root.right != null)
            {
                nodeIds.put(root.right,idGen);
                rightId = idGen;
                idGen++;
                dfs(root.right,nodeIds, sb);
            }
            StringBuilder serial = new StringBuilder("");
            // serial.append("(");
            serial.append(root.val);
            serial.append(":");
            serial.append(leftId);
            serial.append(":");
            serial.append(rightId);
            serial.append(":");
            serial.append(nodeIds.get(root));
            // serial.append(")");
            serial.append(",");
            sb.append(serial);
        }

    }

    // Let us use the ":" within, and the "," outside as well too
    // Decodes your encoded data to tree.
    // State of (-1,-1) denotes no ID here
    // (4:-1:4:3)
    public TreeNode deserialize(String data) 
    {
        if(data.equals("") || data == null)
            return null;
        // Strip that final punctuation at the end BTW
        HashMap<Integer,TreeNode> nodeIds = new HashMap<Integer,TreeNode>();
        String strippedEnd = data.substring(0,data.length());
        String[] nodeInfo = strippedEnd.split(",");
        // First pass : initialize tree nodes
        for(String x : nodeInfo)
        {
            String[] meta = x.split(":");
            int nodeVal = Integer.parseInt(meta[0]);
            int nodeID = Integer.parseInt(meta[3]);
            
            // nodeID is never negative here, luckily
            TreeNode novel = new TreeNode(nodeVal);
            nodeIds.put(nodeID, novel);
        }
        // Second pass : connect left,right pointers
        for(String x : nodeInfo)
        {
            String[] meta = x.split(":");
            int nodeVal = Integer.parseInt(meta[0]);
            int leftID = Integer.parseInt(meta[1]);
            int rightID = Integer.parseInt(meta[2]);
            int nodeID = Integer.parseInt(meta[3]);
            
            // nodeID is never negative here, luckily
            TreeNode novel = nodeIds.get(nodeID);
            if(leftID != -1)
            {
                novel.left = nodeIds.get(leftID);
            }
            if(rightID != -1)
            {
                novel.right = nodeIds.get(rightID);
            }
        }
        
        TreeNode root = nodeIds.get(0);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
