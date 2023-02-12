package Global;

import Model.*;
import java.util.Map;

public class Maps {
    static Map<Integer, Course> courses;
    static Map<Integer, Degree> degrees;
    static Map<Integer, Student> students;

    public static void SetCourseMap(Map<Integer, Course> _map) { courses = _map; }
    public static void SetDegreeMap(Map<Integer, Degree> _map) { degrees = _map; }
    public static void SetStudentMap(Map<Integer, Student> _map) { students = _map; }

    public static Course GetCourseById(int _id) throws Exception {
        if (courses == null)
            throw new Exception("Maps.courses cannot be null");

        Course course = courses.get(_id);

        if (course == null)
            throw new Exception("Could not find course using ID " + _id);

        return course;
    }

    public static Degree GetDegreeById(int _id) throws Exception {
        if (degrees == null)
            throw new Exception("Maps.degrees cannot be null");

        Degree degree = degrees.get(_id);

        if (degree == null)
            throw new Exception("Could not find degree using ID " + _id);

        return degree;
    }

    public static Student GetStudentById(int _id) throws Exception {
        if (students == null)
            throw new Exception("Maps.students cannot be null");

        Student student = students.get(_id);

        if (student == null)
            throw new Exception("Could not find student using ID " + _id);

        return student;
    }
}