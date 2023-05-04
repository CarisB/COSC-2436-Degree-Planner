package View;

import Global.Maps;
import Global.SceneManager;
import Model.CourseOffering;
import Model.Student;
import Model.Course;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CourseDetailScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String NULL_COURSE_ERROR = "Cannot have a null Course!";
    final String COURSE_OFFERINGS_HEADER = "Course offerings available:";
    final String MENU_TEXT = "Select a number to add to schedule, or enter [0] to return to the course list: ";
    final String DUPLICATE_OFFERING_ERROR = "Schedule already contains this course offering!";
    final String INVALID_OPTION_ERROR = "Please enter a valid option: ";
    final int PANEL_LENGTH = 100;
    final String INDENT_STRING = "    ";

    Student student;
    Course course;
    List<CourseOffering> offerings;

    public CourseDetailScene(Student _student, Course _course) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);
        if (_course == null)
            throw new Exception(NULL_COURSE_ERROR);

        student = _student;
        course = _course;
        offerings = Maps.GetCourseOfferingById(course.getId());  // Find the course offerings tied to this course ID
    }

    public void Init() throws Exception {
        DisplayCourse();
        DisplayCourseOfferings();
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
        char sep = '/';

        System.out.println(INDENT_STRING + COURSE_OFFERINGS_HEADER);
        DrawBar(PANEL_LENGTH, '-');

        int index = 1;

        for (CourseOffering offering : offerings) {
            System.out.print("| " + index + " |");
            System.out.print(INDENT_STRING);
            System.out.print(offering.getDate());
            System.out.print(INDENT_STRING + sep + INDENT_STRING);
            System.out.print(offering.getTime());
            System.out.print(INDENT_STRING + sep + INDENT_STRING);
            System.out.print(offering.getInstructor());
            System.out.print(INDENT_STRING + sep + INDENT_STRING);

            switch (offering.getFormat()) {
                case IN_PERSON -> System.out.print("In Person");
                case ONLINE -> System.out.print("Online");
            }

            System.out.println(INDENT_STRING + sep);
            index++;
        }
    }

    void AddToSchedule(CourseOffering _offering) throws Exception {
        List<CourseOffering> schedule = student.getSchedule();

        if (!schedule.contains(_offering)) {
            schedule.add(_offering);
            SceneManager.Next(new InfoScene(student));
        } else {
            System.out.println(DUPLICATE_OFFERING_ERROR);
            WaitForInput();
        }
    }

    void WaitForInput() throws Exception {
        System.out.println();
        System.out.print(MENU_TEXT);

        try {
            Scanner scn = new Scanner(System.in);
            int option = scn.nextInt();

            if (option == 0)  // Return to Dashboard
                SceneManager.Next(new CourseListScene(student));
            else if (option < offerings.size() + 1)  // Valid course selected
                AddToSchedule(offerings.get(option - 1));
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

    void DrawBar(int _size, char _char) { System.out.println(String.valueOf(_char).repeat(_size)); }
    void DrawInlineBar(int _size, char _char) { System.out.print(String.valueOf(_char).repeat(_size)); }
}
