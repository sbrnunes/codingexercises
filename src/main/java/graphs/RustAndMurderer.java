package graphs;

import java.io.IOException;
import java.util.*;

public class RustAndMurderer
{

    public static final int VILLAGE_ROAD = 0;
    public static final int DISCOVERED = 2;
    public static final int ROAD_COST = 1;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();

        for(int i = 0; i < testCases; i++) {
            int cities = scanner.nextInt();
            int roads = scanner.nextInt();

            int[][] adjacentMatrix = new int[cities][cities];

            for (int r = 0; r < roads; r++)
            {
                int origin = scanner.nextInt();
                int destination = scanner.nextInt();

                adjacentMatrix[origin - 1][destination - 1] = 1;
                adjacentMatrix[destination - 1][origin - 1] = 1;
            }

            int start = scanner.nextInt() - 1;

            int[] shortestPaths = findShortestPaths(adjacentMatrix, start);

            for (int p = 0; p < shortestPaths.length; p++)
            {
                if(p != start)
                {
                    System.out.print(shortestPaths[p] + " ");
                }
            }

            System.out.print("\n");
        }

    }

    /**
     * function Dijkstra(Graph, source):
     *
     *   dist[source] ← 0                       // Distance from source to source
     *   prev[source] ← undefined               // Previous node in optimal path initialization
     *
     *   for each vertex v in Graph:  // Initialization
     *       if v ≠ source            // Where v has not yet been removed from Q (unvisited nodes)
     *           dist[v] ← infinity             // Unknown distance function from source to v
     *           prev[v] ← undefined            // Previous node in optimal path from source
     *       end if
     *       add v to Q                     // All nodes initially in Q (unvisited nodes)
     *   end for
     *
     *   while Q is not empty:
     *       u ← vertex in Q with min dist[u]  // Source node in first case
     *       remove u from Q
     *
     *       for each neighbor v of u:           // where v is still in Q.
     *           alt ← dist[u] + length(u, v)
     *           if alt < dist[v]:               // A shorter path to v has been found
     *               dist[v] ← alt
     *               prev[v] ← u
     *           end if
     *       end for
     *   end while
     *
     *   return dist[], prev[]
     *
     * end function
     */
    private static int[] findShortestPaths(int[][] adjacentMatrix, int start)
    {
        int[] distanceToNodes = new int[adjacentMatrix.length];

        for (int i = 0; i < distanceToNodes.length; i++)
        {
            distanceToNodes[i] = Integer.MAX_VALUE;
        }

        distanceToNodes[start] = 0;

        Queue<Integer> queue = new PriorityQueue<>((node1, node2) -> {
            return Integer.compare(distanceToNodes[node1], distanceToNodes[node2]);
        });

        queue.add(start);

        while(!queue.isEmpty())
        {
            Integer node = queue.poll();

            if(adjacentMatrix[node][node] == DISCOVERED)
            {
                continue;
            }

            adjacentMatrix[node][node] = DISCOVERED; //node has been discovered

            for (int neighbour = 0; neighbour < adjacentMatrix[node].length; neighbour++)
            {
                if(neighbour == node || adjacentMatrix[neighbour][neighbour] == DISCOVERED)
                {
                    continue;
                }

                if(adjacentMatrix[node][neighbour] == VILLAGE_ROAD) //not main road
                {
                    distanceToNodes[neighbour] = Math.min(distanceToNodes[neighbour], distanceToNodes[node] + ROAD_COST);

                    queue.add(neighbour);
                }
            }
        }

        return distanceToNodes;
    }
}
