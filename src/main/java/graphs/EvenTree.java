package graphs;

import java.util.*;

public class EvenTree
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nVertices = scanner.nextInt();
        int nEdges = scanner.nextInt();

        Vertex[] vertices = new Vertex[nVertices];

        for(int i = 0; i < nVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        for(int i = 0; i < nEdges; i++) {
            int ui = scanner.nextInt();
            int vi = scanner.nextInt();

            vertices[ui - 1].edges.add(vi - 1);
            vertices[vi - 1].edges.add(ui - 1);
        }

        int removedEdges = getEvenTrees(vertices);

        System.out.println(removedEdges);
    }

    private static int getEvenTrees(Vertex[] vertices) {

        dfs(vertices, vertices[0]); //O ( |E| + |V| )

        int[] subTreeSizes = new int[vertices.length];

        for (Vertex vertice : vertices)
        {
            subTreeSizes[vertice.label] = vertice.subTreeSize;
        }

        Arrays.sort(subTreeSizes);

        int nRemovedEdges = 0;
        for(int i = 0; i < subTreeSizes.length - 1; i ++) { //last position is the root of the tree
            if(subTreeSizes[i] % 2 == 0) {
                nRemovedEdges++;
            }
        }

        return nRemovedEdges;
    }

    private static int dfs(Vertex[] vertices, Vertex current) {

        current.subTreeSize++; //the vertice itself is part of the subtree

        current.visited = true;

        current.edges.forEach(vertex -> {
            if(!vertices[vertex].visited) {
                current.subTreeSize += dfs(vertices, vertices[vertex]);
            }
        });

        return current.subTreeSize;
    }

    private static class Vertex {
        int label;
        int subTreeSize = 0;
        boolean visited = false;
        Set<Integer> edges = new HashSet<>();

        public Vertex(int label) {
            this.label = label;
        }
    }
}
