package graphs;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseScheduleTest
{

    @Test
    public void testIsCourseSchedulePossible() throws Exception
    {

        assertTrue(CourseSchedule.isCourseSchedulePossible(2, new int[][]{}));

        assertTrue(CourseSchedule.isCourseSchedulePossible(2, new int[][]{
                {0, 1}
        }));

        assertFalse(CourseSchedule.isCourseSchedulePossible(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        }));

        assertFalse(CourseSchedule.isCourseSchedulePossible(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 1}
        }));

        assertTrue(CourseSchedule.isCourseSchedulePossible(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {2, 4},
                {4, 5},
        }));

    }

    @Test
    public void testIsCourseSchedulePossible2() throws Exception
    {

        assertTrue(CourseSchedule.isCourseSchedulePossible2(2, new int[][]{}));

        assertTrue(CourseSchedule.isCourseSchedulePossible2(2, new int[][]{
                {0, 1}
        }));

        assertFalse(CourseSchedule.isCourseSchedulePossible2(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        }));

        assertFalse(CourseSchedule.isCourseSchedulePossible2(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 1}
        }));

        assertTrue(CourseSchedule.isCourseSchedulePossible2(10, new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {2, 4},
                {4, 5},
        }));

    }
}