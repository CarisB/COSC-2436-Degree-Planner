package Global;

import Classes.Course;
import Classes.Major;
import Classes.Student;
import java.util.Map;

public class Maps {
    static Map<Integer, Course> courses;
    static Map<Integer, Major> majors;
    static Map<Integer, Student> students;

    public static Map<Integer, Course> Courses() { return courses; }
    public static Map<Integer, Major> Majors() { return majors; }
    public static Map<Integer, Student> Students() { return students; }

    public static Map<Integer, Course> SetCourseMap(Map<Integer, Course> _map) {
        courses = _map;
        return courses;
    }

    public static Map<Integer, Major> SetMajorMap(Map<Integer, Major> _map) {
        majors = _map;
        return majors;
    }

    public static Map<Integer, Student> SetStudentMap(Map<Integer, Student> _map) {
        students = _map;
        return students;
    }

    public static Course GetCourseById(int _id) throws Exception {
        if (courses == null)
            throw new Exception("MMaps.courses cannot be null");

        Course course = courses.get(_id);

        if (course == null)
            throw new Exception("Course " + _id + " not found in Maps.courses");

        return course;
    }

    public static Major GetMajorById(int _id) throws Exception {
        if (majors == null)
            throw new Exception("Maps.majors cannot be null");

        Major major = majors.get(_id);

        if (major == null)
            throw new Exception("Major " + _id + "not found in Maps.majors");

        return major;
    }

    public static Student GetStudentById(int _id) throws Exception {
        if (students == null)
            throw new Exception("Maps.students cannot be null");

        Student student = students.get(_id);

        if (student == null)
            throw new Exception("Student " + _id + " not found in Maps.students");

        return student;
    }
}
