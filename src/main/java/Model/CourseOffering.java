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
}
