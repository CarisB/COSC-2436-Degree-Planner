package View;

import Global.Maps;
import Global.SceneManager;
import Model.Student;
import Model.Degree;
import Model.Course;

public class DegreeScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String INVALID_COURSE_ERROR = "*** ERROR: Cannot find course by ID ";
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
        DrawBar(PANEL_LENGTH, '=');
        System.out.print(INDENT_STRING);
        System.out.println(degree.getName());
        DrawBar(PANEL_LENGTH, '=');
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
        DrawBar(50, '-');

        for (int i = 0; i < _courses.length; i++) {
            int id = _courses[i].getId();

            try {   // Validate that ID exists in Course map
                Course course = Maps.GetCourseById(id);

                System.out.print(INDENT_STRING);
                System.out.print(INDENT_STRING);
                System.out.print(course.getName() + " - ");
                System.out.println(course.getCredits() + " Credits");
            }
            catch (Exception e) {   // Course cannot be found
                InvalidCourseError(id);
            }
        }
    }

    void WaitForInput() throws Exception {
        System.out.print(RETURN_MSG);
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }

    void InvalidCourseError(int _id) {
        System.out.println(INVALID_COURSE_ERROR + _id);
    }
    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
