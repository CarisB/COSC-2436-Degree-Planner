package View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Global.SceneManager;
import Model.Student;
import Model.Degree;
import Model.Course;

public class CourseListScene implements IScene {
    final String HEADER_MSG = "List of recommended courses";
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String COURSE_LIST_EMPTY = "There are no courses to recommend at this time.";
    final String MENU_TEXT = "Select a number to view a course, or enter [0] to return to the dashboard: ";
    final String INVALID_OPTION_ERROR = "Please enter a valid option: ";
    final int PANEL_LENGTH = 100;
    final String INDENT_STRING = "    ";

    Student student;
    Degree degree;
    List<Course> courseList;

    public CourseListScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
        degree = student.getDegree();
        courseList = new ArrayList<>();
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

        courseList = ConcatArraysAsList(arrays);

        if (!courseList.isEmpty())
            for (int i = 0; i < courseList.size(); i++)
                DisplayEntry(i + 1, courseList.get(i));  // Displays the course, along with an index number (for menu)
        else
            System.out.println(INDENT_STRING + COURSE_LIST_EMPTY);

        DrawBar(PANEL_LENGTH, '-');
        System.out.print(MENU_TEXT);
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
        try {
            Scanner scn = new Scanner(System.in);
            int option = scn.nextInt();

            // Load next Scene
            if (option == 0)  // Return to Dashboard
                SceneManager.Next(new DashboardScene(student));
            else if (option < courseList.size() + 1)  // Valid course selected
                SceneManager.Next(new CourseDetailScene(student, courseList.get(option)));
            else  // Invalid input -> Retry()
                Retry();

        } catch (InputMismatchException e) {
            Retry();
        }
    }

    void Retry() throws Exception {
        System.out.print(INVALID_OPTION_ERROR);
        WaitForInput();
    }

    <T> List<T> ConcatArraysAsList(T[][] _arrays) {

        List<T> result = new ArrayList<>();

        for (T[] array : _arrays)
            for (T entry : array)
                if (!student.getTranscript().containsKey(entry))  // Only add if the student hasn't already completed the course
                    result.add(entry);

        return result;
    }

    void DrawBar(int _size, char _char) { System.out.println(String.valueOf(_char).repeat(_size)); }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
