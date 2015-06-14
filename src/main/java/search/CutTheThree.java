package search;

import java.util.*;

@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public class CutTheThree
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int nvertices = scanner.nextInt();

        Vertex[] vertices = new Vertex[nvertices];

        for (int i = 0; i < nvertices; i++)
        {
            vertices[i] = new Vertex(i, scanner.nextInt());
        }

        for (int i = 0; i < nvertices - 1; i++)
        {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();

            vertices[source - 1].edges.add(destination - 1);
            vertices[destination - 1].edges.add(source - 1);
        }

        calculateMaximumTreeWeights(vertices, vertices[0]);

        int minimumTreeDiff = findMinimumTreeDiff(vertices);

        System.out.println(minimumTreeDiff);
    }

    private static int calculateMaximumTreeWeights(Vertex[] vertices, Vertex vertice) //n
    {
        vertice.discovered = true;

        if(vertice.edges.isEmpty()) {
            return vertice.treeWeight;
        }

        vertice.edges.forEach(destination -> {

            Vertex nextVertex = vertices[destination];

            if (!nextVertex.discovered)
            {
                vertice.treeWeight += calculateMaximumTreeWeights(vertices, nextVertex);
            }
        });

        return vertice.treeWeight;
    }

    private static int findMinimumTreeDiff(Vertex[] vertices)
    {
        Vertex originalTreeRoot = vertices[0];

        int minimumTreeDiff = Integer.MAX_VALUE;

        //root is at 0
        for (int i = 1; i < vertices.length; i++) //n
        {
            Vertex newTreeRoot = vertices[i];

            int originalTreeUpdatedWeight = originalTreeRoot.treeWeight - newTreeRoot.treeWeight;
            int newTreeWeight = newTreeRoot.treeWeight;
            int diff = Math.abs(originalTreeUpdatedWeight - newTreeWeight);

            minimumTreeDiff = Math.min(minimumTreeDiff, diff);
        }

        return minimumTreeDiff;
    }

    private static class Vertex {
        int key;
        int weigth;
        int treeWeight;
        boolean discovered;

        Set<Integer> edges = new HashSet<>();

        public Vertex(int key, int weigth)
        {
            this.key = key;
            this.weigth = weigth;
            this.treeWeight = weigth;
        }
    }

}
