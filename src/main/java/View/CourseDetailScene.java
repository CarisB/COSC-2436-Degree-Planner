package View;

import Global.Maps;
import Global.SceneManager;
import Model.Student;
import Model.Course;

public class CourseDetailScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String NULL_COURSE_ERROR = "Cannot have a null Course!";
    final String COURSE_OFFERINGS_HEADER = "Course offerings available:";
    final int PANEL_LENGTH = 100;
    final String INDENT_STRING = "    ";

    Student student;
    Course course;

    public CourseDetailScene(Student _student, Course _course) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);
        if (_course == null)
            throw new Exception(NULL_COURSE_ERROR);

        student = _student;
        course = _course;
    }

    public void Init() throws Exception {
        DisplayCourse();
        WaitForInput();
    }

    void DisplayCourse() throws Exception {
        DrawBar(PANEL_LENGTH, '=');
        System.out.println();
        System.out.print(INDENT_STRING + course.getName());
        System.out.println(INDENT_STRING + "(" + course.getCredits() + " SCH)");
        DrawBar(PANEL_LENGTH, '-');
        System.out.println(INDENT_STRING + course.getDescription());
        DisplayPrereqs();
    }

    void DisplayPrereqs() throws Exception {
        System.out.print(INDENT_STRING + "[Prerequisites: ");

        int[] prereqs = course.getPrereqs();

        if (prereqs.length == 0)  // No prereqs
            System.out.print("N/A");

        for (int i = 0; i < prereqs.length; i++) {
            String name = Maps.GetCourseById(prereqs[i]).getName();
            System.out.print(name);

            if (i != prereqs.length - 1)  // Not last entry in prereqs
                System.out.print(", ");
        }

        System.out.println("]");
        System.out.println();
    }

    void DisplayCourseOfferings() {
        System.out.println(COURSE_OFFERINGS_HEADER);
        DrawBar(PANEL_LENGTH, '-');

    }

    void WaitForInput() throws Exception {
        System.in.read();
    }

    void DrawBar(int _size, char _char) { System.out.println(String.valueOf(_char).repeat(_size)); }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
