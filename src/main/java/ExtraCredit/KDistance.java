package ExtraCredit;

import Problem1.TreeNode;
import Problem2.LCA;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class KDistance
{
    //Note: I am stealing my findNode method from my solution to Problem2 to find the common ancestor to help find k

    public static List<Integer> distanceK(TreeNode<Integer> root, TreeNode<Integer> target, int k)
    {
        List<Integer> results = new ArrayList<>();
        if(k == 0) { results.add(target.val); return results; }

        traverse(root, root, target, k, results);
        return results;
    }

    //goes through each value in the tree and runs findDist, never touches totalRoot, target, or k, results is how it passes out what it finds
    private static void traverse(TreeNode<Integer> curr, TreeNode<Integer> totalRoot, TreeNode<Integer> target, int k, List<Integer> results)
    {
        if(findDist(totalRoot, target, curr) == k)
            results.add(curr.val);
        if(curr.left != null)
            traverse(curr.left, totalRoot, target, k, results);
        if(curr.right != null)
            traverse(curr.right, totalRoot, target, k, results);
    }

    //first, it finds the lowest common ancestor, then it finds the distance between the LCA and each value, then adds them together
    public static int findDist(TreeNode<Integer> root, TreeNode<Integer> target, TreeNode<Integer> curr)
    {
        TreeNode<Integer> lca = LCA.lowestCommonAncestor(root, target, curr);
        int a = LCA.findNode(lca, target).size() - 1;
        int b = LCA.findNode(lca, curr).size() - 1;
        return a + b;
    }
}
