package graphs;

import java.util.LinkedList;
import java.util.Queue;

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
        for (int i = 0; i < prerequisites.length; i++)
        {
            numberOfPrerequisites[prerequisites[i][0]]++;
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
            Integer course = queue.poll();

            for (int i = 0; i < prerequisites.length; i++)
            {
                // if a course's prerequisite can be satisfied by a course in queue
                if(prerequisites[i][1] == course)
                {
                    numberOfPrerequisites[prerequisites[i][0]]--;

                    if(numberOfPrerequisites[prerequisites[i][0]] == 0)
                    {
                        coursesWithoutPrerequisites++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return coursesWithoutPrerequisites == numberOfCourses;
    }
}
