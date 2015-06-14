package search;

import java.util.*;

public class SimilarPair
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int t = scanner.nextInt();

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < n - 1; i++)
        {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();

            nodes[source - 1].edges.add(destination - 1);
            nodes[destination - 1].ancestor = nodes[source - 1];
        }

        Node root = findRoot(nodes);

        System.out.println(dfs(nodes, root, t));
    }

    private static Node findRoot(Node[] nodes)
    {
        for (Node node : nodes)
        {
            if(node.ancestor == null) {
                return node;
            }
        }

        throw new RuntimeException("No root node.");
    }

    private static int dfs(Node[] nodes, Node current, int t)
    {
        current.visited = true;

        int similarPairs = findSimilarPairs(current, t);

        for (Integer edge : current.edges)
        {
            Node next = nodes[edge];

            if(!next.visited) {
                similarPairs += dfs(nodes, next, t);
            }
        }

        return similarPairs;
    }

    private static int findSimilarPairs(Node current, int t)
    {
        int similarPairs = 0;

        Node ancestor = current.ancestor;
        while(ancestor != null) {

            if(Math.abs(ancestor.label - current.label) <= t) {
                similarPairs++;
            }

            ancestor = ancestor.ancestor;
        }

        return similarPairs;
    }

    private static class Node
    {
        int label;
        Node ancestor;
        boolean visited;
        Set<Integer> edges = new HashSet<>();

        public Node(int label)
        {
            this.label = label;
        }
    }
}
