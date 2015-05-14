package graphs;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites,
 * for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1].
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * For example, given 2 and [[1,0]], there are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * For another example, given 2 and [[1,0],[0,1]], there are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class CourseSchedule
{

    public static boolean isCourseSchedulePossible(int numberOfCourses, int[][] prerequisites)
    {
        if(prerequisites == null || prerequisites.length == 0)
        {
            return true;
        }

        int[] numberOfPrerequisites = new int[numberOfCourses];

        //Number of prerequisites for each course
        for (int[] prerequisite : prerequisites)
        {
            numberOfPrerequisites[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        //Puts all the courses without prerequisites in the queue
        for (int i = 0; i < numberOfCourses; i++)
        {
            if(numberOfPrerequisites[i] == 0)
            {
                queue.add(i);
            }
        }

        int coursesWithoutPrerequisites = queue.size();

        while(!queue.isEmpty())
        {
            int course = queue.poll();

            for (int[] prerequisite : prerequisites)
            {
                // if a course's prerequisite can be satisfied by a course in the queue
                if (prerequisite[1] == course)
                {
                    numberOfPrerequisites[prerequisite[0]]--;

                    if (numberOfPrerequisites[prerequisite[0]] == 0)
                    {
                        coursesWithoutPrerequisites++;

                        queue.add(prerequisite[0]);
                    }
                }
            }
        }

        return coursesWithoutPrerequisites == numberOfCourses;
    }

    public static boolean isCourseSchedulePossible2(int numberOfCourses, int[][] prerequisites)
    {
        if(prerequisites == null || prerequisites.length == 0)
        {
            return true;
        }

        GraphNode[] nodes = new GraphNode[numberOfCourses];
        for (int i = 0; i < numberOfCourses; i++)
        {
            nodes[i] = new GraphNode(i);
        }

        for (int[] prerequisite : prerequisites)
        {
            int course = prerequisite[0];
            int dependency = prerequisite[1];

            nodes[course].addNeighbour(nodes[dependency]);
        }

        Stack<GraphNode> stack = new Stack<>();

        for (GraphNode node : nodes)
        {
            stack.push(node);
            if (!canFinishCourse(stack))
            {
                return false;
            }
        }

        return true;
    }

    private static boolean canFinishCourse(Stack<GraphNode> stack)
    {
        if(stack.isEmpty())
        {
            return true;
        }

        GraphNode node = stack.pop();

        if(node.isVisited())
        {
            return false;
        }

        node.setVisited(true);

        for (GraphNode neighbour : node.getNeighbours())
        {
            stack.push(neighbour);
            if(!canFinishCourse(stack))
            {
                return false;
            }
        }

        node.setVisited(false);

        return true;

    }

    private static class GraphNode
    {
        private int vertex;
        private List<GraphNode> neighbours = new ArrayList<>();
        boolean visited = false;

        GraphNode(int vertex)
        {
            this.vertex = vertex;
        }

        public void addNeighbour(GraphNode neighbour) {
            neighbours.add(neighbour);
        }

        public List<GraphNode> getNeighbours()
        {
            return neighbours;
        }

        public boolean isVisited()
        {
            return visited;
        }

        public void setVisited(boolean visited)
        {
            this.visited = visited;
        }
    }
}
