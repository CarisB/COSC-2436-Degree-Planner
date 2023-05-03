package View;

import java.util.ArrayList;
import java.util.List;
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

        // Create an array of course arrays to convert to a List<Course> with ConcatArrayAsList()
        Course[][] arrays = {
                degree.getMajorReq(),
                degree.getCore(),
                degree.getElective()
        };

        List<Course> courseList = ConcatArraysAsList(arrays);

        for (int i = 0; i < courseList.size(); i++)
            DisplayEntry(i, courseList.get(i));  // Displays the course, along with an index number (for menu)

        DrawBar(PANEL_LENGTH, '-');
    }

    void DisplayEntry(int _i, Course _course) {
        char border = '|';

        System.out.print(border + " " + _i + " " + border);
        System.out.print(INDENT_STRING);
        System.out.print(_course.getName());
        System.out.print(INDENT_STRING);
        System.out.print(_course.getCredits());
        System.out.print(" credits");
        System.out.println();
    }

    void WaitForInput() throws Exception {
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }

    <T> List<T> ConcatArraysAsList(T[][] _arrays) {

        List<T> result = new ArrayList<>();

        for (T[] array : _arrays)
            for (T entry : array)
                if (!student.getTranscript().containsKey(entry))  // Only add if the student hasn't already completed the course
                    result.add(entry);

        return result;
    }

    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
