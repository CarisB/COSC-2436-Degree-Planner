package Classes;

import Helpers.Enums.CourseFormat;

public class CourseOffering {
    Course course;
    String time;
    String instructor;
    CourseFormat format;

    public Course getCourse() { return course; }
    public String getTime() { return time; }
    public String getInstructor() { return instructor; }
    public CourseFormat getFormat() { return format; }
}
