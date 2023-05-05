package View;

import Global.SceneManager;
import Model.Student;
import Model.Degree;
import Model.Course;
import Helpers.UI;

import java.util.Scanner;

public class DegreeScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String RETURN_MSG = "[ Press ENTER to return to the Dashboard ]";
    final String INDENT_STRING = "    ";
    final int PANEL_LENGTH = 100;

    Student student;
    Degree degree;

    public DegreeScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
        degree = student.getDegree();
    }

    public void Init() throws Exception {
        DisplayInfo();
        WaitForInput();
    }

    void DisplayInfo() {
        UI.DrawBar(PANEL_LENGTH, '=');
        System.out.print(INDENT_STRING);
        System.out.println(degree.getName());
        UI.DrawBar(PANEL_LENGTH, '=');
        DisplayCoursesSection(degree.getMajorReq(), degree.getMajorReqCredits(), "Major-related");
        DisplayCoursesSection(degree.getCore(), degree.getCoreCredits(), "Core");
        DisplayCoursesSection(degree.getElective(), degree.getElectiveCredits(), "Elective");
        System.out.println();
    }

    void DisplayCoursesSection(Course[] _courses, int _credits, String _type) {
        System.out.println();
        System.out.print(INDENT_STRING);
        System.out.println(_type + " courses: " + _credits + " total credits required");
        System.out.print(INDENT_STRING);
        UI.DrawBar(50, '-');

        for (Course course : _courses) {
            System.out.print(INDENT_STRING);
            System.out.print(INDENT_STRING);
            System.out.print(course.getName() + " - ");
            System.out.println(course.getCredits() + " Credits");
        }
    }

    void WaitForInput() throws Exception {
        System.out.print(INDENT_STRING);
        System.out.print(RETURN_MSG);
        Scanner scn = new Scanner(System.in);
        scn.nextLine();
        SceneManager.Next(new DashboardScene(student));
    }
}
