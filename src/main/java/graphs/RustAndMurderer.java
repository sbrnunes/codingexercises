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

            int[][] mainRoads = new int[cities][cities];

            for (int r = 0; r < roads; r++)
            {
                int origin = scanner.nextInt();
                int destination = scanner.nextInt();

                mainRoads[origin - 1][destination - 1] = 1;
                mainRoads[destination - 1][origin - 1] = 1;
            }

            int start = scanner.nextInt() - 1;

            int[] shortestPaths = findShortestPaths(start, mainRoads);

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

    private static int[] findShortestPaths(int start, int[][] map)
    {
        int[] distanceToNodes = new int[map.length];

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

            if(map[node][node] == DISCOVERED)
            {
                continue;
            }

            map[node][node] = DISCOVERED; //discovered

            for (int neighbour = 0; neighbour < map[node].length; neighbour++)
            {
                if(map[neighbour][neighbour] == DISCOVERED)
                {
                    continue;
                }

                if(neighbour != node && map[node][neighbour] == VILLAGE_ROAD) //not main road
                {
                    distanceToNodes[neighbour] = Math.min(distanceToNodes[neighbour], distanceToNodes[node] + ROAD_COST);

                    queue.add(neighbour);
                }
            }
        }

        return distanceToNodes;
    }
}
