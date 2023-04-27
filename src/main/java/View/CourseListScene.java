package View;

import Global.SceneManager;
import Model.Student;
import Model.Degree;
import Model.Course;

public class CourseListScene implements IScene {
    final String HEADER_MSG = "List of recommended courses";
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final int PANEL_LENGTH = 100;
    final String INDENT_STRING = "    ";

    Student student;
    Degree degree;

    public CourseListScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
        degree = student.getDegree();
    }

    public void Init() throws Exception {
        DisplayHeader();
        DisplayCourseList();
        WaitForInput();
    }

    void DisplayHeader() {
        DrawBar(PANEL_LENGTH, '=');
        System.out.print(INDENT_STRING);
        System.out.println(HEADER_MSG);
        DrawBar(PANEL_LENGTH, '=');
    }

    void DisplayCourseList() {
        DrawBar(PANEL_LENGTH, '-');

        for (Course course : degree.getMajorReq()) { DisplayEntry(course); }
        for (Course course : degree.getCore()) { DisplayEntry(course); }
        for (Course course : degree.getElective()) { DisplayEntry(course); }

        DrawBar(PANEL_LENGTH, '-');
    }

    void DisplayEntry(Course _course) {
        char border = '|';

        System.out.print(border + INDENT_STRING);
        System.out.print(_course.getName());
        System.out.print(INDENT_STRING + INDENT_STRING);
        System.out.print(_course.getCredits());
        System.out.print(" credits");
        System.out.println();
    }

    void WaitForInput() throws Exception {
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }

    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
