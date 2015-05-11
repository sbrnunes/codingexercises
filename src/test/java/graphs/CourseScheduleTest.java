package graphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseScheduleTest
{

    @Test
    public void testIsCourseSchedulePossible() throws Exception
    {

        assertEquals(true, CourseSchedule.isCourseSchedulePossible(2, new int[][]{}));


        assertEquals(true, CourseSchedule.isCourseSchedulePossible(2, new int[][]{
                {0, 1}
        }));

        assertEquals(false, CourseSchedule.isCourseSchedulePossible(2, new int[][]{
                {0, 1},
                {1, 0}
        }));

    }
}