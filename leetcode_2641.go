/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 /*
 URL = https://leetcode.com/problems/cousins-in-binary-tree-ii/
 2641. Cousins in Binary Tree II
 Classic level order traversal
 Modify the tree root in place too
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
    firstLot := [](*TreeNode){}
    firstLot = append(firstLot, root)
    lot(firstLot)
    root.Val = 0
    return root
}

// null : not void
// current level := all parents of a given generatoin
// compute sums : change children in place
func lot(level []*TreeNode) {
    if level == nil {
        return
    }
    numParents := len(level)
    if numParents == 0 {
        return
    }
    cousinSums := make([]int, numParents)
    for i, _ := range level {
        myCousinSum := 0
        for j, myTreeNode := range level {
            if i != j {
                // fmt.Printf("%v\n", myTreeNode.Left)
                if myTreeNode.Left != nil {
                    myCousinSum += myTreeNode.Left.Val
                }
                if myTreeNode.Right != nil {
                    myCousinSum += myTreeNode.Right.Val
                }
            }
        }
        cousinSums[i] = myCousinSum
    }
    for idx, myTreeNode := range level {
        if myTreeNode.Left != nil {
            myTreeNode.Left.Val = cousinSums[idx]
        }
        if myTreeNode.Right != nil {
            myTreeNode.Right.Val = cousinSums[idx]
        }
    }
    // Construct child level node
    childLevel := [](*TreeNode){}
    for _, myTreeNode := range level {
        if myTreeNode.Left != nil {
            childLevel = append(childLevel,myTreeNode.Left)
        }
        if myTreeNode.Right != nil {
            childLevel = append(childLevel,myTreeNode.Right)
        }
    }
    lot(childLevel)
}
Console
