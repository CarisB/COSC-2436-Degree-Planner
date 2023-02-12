/**
 * Degree Planner tool created for COSC 2436.
 * A student can log in using an ID and register degree-relevant classes to their schedule.
 *
 * @author  Christopher Baek
 * @version 1.0
 * @since   2023-02-08
 */

import UI.Scene;

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

        studentMap.forEach((id, student) -> {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Major: " + student.getMajor().name);
            System.out.println("Transcript:");

            student.getTranscript().forEach((course, grade) ->
                    System.out.println("- " + course.name + ": " + grade));

            System.out.println();
        });
    }
}