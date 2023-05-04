package Model;

import Helpers.Enums.CourseFormat;

public class CourseOffering {
    Course course;
    String date;
    String time;
    String instructor;
    CourseFormat format;

    public Course getCourse() { return course; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getInstructor() { return instructor; }
    public CourseFormat getFormat() { return format; }

    public CourseOffering(Course _course,
                          String _date,
                          String _time,
                          String _instructor,
                          CourseFormat _format) {
        course = _course;
        date = _date;
        time = _time;
        instructor = _instructor;
        format = _format;
    }
}
