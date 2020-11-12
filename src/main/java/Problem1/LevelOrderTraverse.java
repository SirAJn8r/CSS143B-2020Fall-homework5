package Problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class LevelOrderTraverse {
    public static List<List<Integer>> levelOrder(TreeNode<Integer> root)
    {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> prevLayer = new ArrayBlockingQueue<TreeNode>(20);
        Queue<TreeNode> newLayer = new ArrayBlockingQueue<TreeNode>(20);
        if(root == null) return result;
        prevLayer.add(root);

        while(true)
        {
            newLayer.clear();
            List<Integer> tempResult = new ArrayList<>();

            int loops = prevLayer.size();
            for(int i = 0; i < loops; i++)
            {
                TreeNode<Integer> node = prevLayer.poll();
                tempResult.add((Integer) node.val);
                if(node.left != null)
                    newLayer.add(node.left);
                if(node.right != null)
                    newLayer.add(node.right);
            }
            result.add(tempResult);

            //the end condition
            if(newLayer.isEmpty())
                break;

            //put newLayer into prevLayer
            prevLayer.clear();
            loops = newLayer.size();
            for(int i = 0; i < loops; i++)
                prevLayer.add( newLayer.poll() );
        }

        return result;
    }
}
