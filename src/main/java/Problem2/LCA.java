package Problem2;

import Problem1.TreeNode;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class LCA
{
    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q)
    {
        ArrayBlockingQueue<TreeNode<Integer>> pTree = findNode(root, p);

        while(!pTree.isEmpty())
        {
            TreeNode<Integer> node = pTree.poll();
            if(findNode(node, q) != null)
                return node;
        }

        return null;
    }
    //returns the path from root to node with lower being at the front of the queue, returns null if it cant find node
    public static ArrayBlockingQueue<TreeNode<Integer>> findNode(TreeNode<Integer> root, TreeNode<Integer> node)
    {
        if(root == node)
        {
            ArrayBlockingQueue<TreeNode<Integer>> temp = new ArrayBlockingQueue<>(20);
            temp.add(root);
            return temp;
        }

        //this is to find the node lower down
        ArrayBlockingQueue<TreeNode<Integer>> foundIt = null;
        ArrayBlockingQueue<TreeNode<Integer>> challenger = null;
        if(root.left != null) foundIt = findNode(root.left, node);
        if(root.right != null) challenger = findNode(root.right, node);
        //foundIt will be the stack from the branch it found it on, null if neither branch had the value
        if(foundIt == null) foundIt = challenger;
        if(foundIt == null) return null;

        foundIt.add(root);
        return foundIt;
    }
}
