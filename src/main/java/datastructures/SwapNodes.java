package datastructures;

import java.io.IOException;
import java.util.*;

public class SwapNodes
{

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int numNodes = scanner.nextInt();

        TreeNode[] tree = new TreeNode[numNodes];

        for(int i = 0; i < numNodes; i++)
        {
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            if(tree[i] == null)
            {
                tree[i] = new TreeNode(i+1);
            }

            if(left != -1)
            {
                tree[left-1] = new TreeNode(left);
                tree[i].left = tree[left-1];
            }

            if(right != -1)
            {
                tree[right-1] = new TreeNode(right);
                tree[i].right = tree[right-1];
            }
        }

        int numLevelsToSwap = scanner.nextInt();

        int[] levelsToSwap = new int[numLevelsToSwap];

        for(int i = 0; i < numLevelsToSwap; i++)
        {
            levelsToSwap[i] = scanner.nextInt();
        }

        swapTree(tree[0], levelsToSwap);

    }

    private static void swapTree(TreeNode node, int[] levelsToSwap)
    {
        Stack<TreeNode> stack = new Stack<>();

        for (int levelToSwap : levelsToSwap)
        {
            stack.clear();
            stack.push(node);
            findAndSwap(stack, 0, levelToSwap);
            System.out.println();
        }
    }

    //In-Order traverse and swap
    private static void findAndSwap(Stack<TreeNode> stack, int currentLevel, int levelToSwap)
    {
        currentLevel++;

        TreeNode node = stack.pop();

        if(node.left == null && node.right == null)
        {
            printNode(node);
            return;
        }

        if(currentLevel % levelToSwap == 0)
        {
            swapNode(node);
        }

        if(node.left != null)
        {
            stack.push(node.left);
            findAndSwap(stack, currentLevel, levelToSwap);
        }

        printNode(node);

        if(node.right != null)
        {
            stack.push(node.right);
            findAndSwap(stack, currentLevel, levelToSwap);
        }
    }

    private static void swapNode(TreeNode node)
    {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    private static void printNode(TreeNode node)
    {
        System.out.print(node.id + " ");
    }

    private static class TreeNode {
        private int id;
        private TreeNode left;
        private TreeNode right;


        public TreeNode(int id)
        {
            this.id = id;
        }

    }
}
