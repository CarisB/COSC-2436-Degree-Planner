package Global;

import Model.*;
import java.util.Map;

public class Maps {
    static Map<Integer, Course> courses;
    static Map<Integer, Major> majors;
    static Map<Integer, Student> students;

    public static void SetCourseMap(Map<Integer, Course> _map) { courses = _map; }
    public static void SetMajorMap(Map<Integer, Major> _map) { majors = _map; }
    public static void SetStudentMap(Map<Integer, Student> _map) { students = _map; }

    public static Course GetCourseById(int _id) throws Exception {
        if (courses == null)
            throw new Exception("Maps.courses cannot be null");

        Course course = courses.get(_id);

        if (course == null)
            throw new Exception("Could not find course using ID " + _id);

        return course;
    }

    public static Major GetMajorById(int _id) throws Exception {
        if (majors == null)
            throw new Exception("Maps.majors cannot be null");

        Major major = majors.get(_id);

        if (major == null)
            throw new Exception("Could not find major using ID " + _id);

        return major;
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