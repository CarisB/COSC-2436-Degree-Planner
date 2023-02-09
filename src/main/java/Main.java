/**
 * Degree Planner tool created for COSC 2436.
 * A student can log in using an ID and register degree-relevant classes to their schedule.
 *
 * @author  Christopher Baek
 * @version 1.0
 * @since   2023-02-08
 */

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream stream;
        stream = Main.class.getClassLoader().getResourceAsStream("courses.json");
        var courseMap = Maps.SetCourseMap(DataCreator.CreateCourseMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream("majors.json");
        var majorMap = Maps.SetMajorMap(DataCreator.CreateMajorMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream("students.json");
        var studentMap = Maps.SetStudentMap(DataCreator.CreateStudentMapFromJSON(stream));

        courseMap.forEach((id, course) -> {
            System.out.println(course.getId());
            System.out.println(course.getName());
            System.out.println(course.getDescription());
            System.out.println(course.getCredits());
            for (int i = 0; i < course.getPrereqs().length; i++) {
                System.out.println("Prereq: " + course.getPrereqs()[i]);
            }
            System.out.println();
        });
    }
}