package View;

import Global.SceneManager;
import Model.CourseOffering;
import Model.Student;
import Helpers.UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class InfoScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String INDENT_STRING = "    ";
    final String TRANSCRIPT_HEADER = "TRANSCRIPT";
    final String SCHEDULE_HEADER = "SCHEDULE";
    final String CREDIT_HOURS = " SCH";
    final String COURSE_REMOVED_MSG = "Course offering has been successfully removed from the schedule!";
    final String MENU_TEXT = "Select a number to remove from the schedule, or enter [0] to return to the dashboard: ";
    final String RETURN_MSG = "[ Press ENTER to return to the Dashboard ]";
    final String INVALID_OPTION_ERROR = "Please enter a valid option.";
    final int PANEL_LENGTH = 100;

    Student student;
    List<CourseOffering> schedule;
    String message;

    public InfoScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
        schedule = student.getSchedule();
    }

    public InfoScene(Student _student, String _message) throws Exception {
        this(_student);
        message = _message;
    }

    public void Init() throws Exception {
        DisplayMessage();
        DisplayInfo();
        DisplayTranscript();
        DisplaySchedule();
        WaitForInput();
    }

    void DisplayMessage() {
        if (message == null)
            return;

        UI.DrawBar(PANEL_LENGTH, '/');
        System.out.print(INDENT_STRING);
        System.out.println(message);
    }

    void DisplayInfo() {
        UI.DrawBar(PANEL_LENGTH, '/');
        System.out.println(INDENT_STRING + student.getName());
        System.out.println(INDENT_STRING + student.getDegree().getName());
        UI.DrawBar(PANEL_LENGTH, '/');
        System.out.println();
    }

    void DisplayTranscript() {
        char border = '=';
        String sep = " - ";
        UI.DrawInlineBar(INDENT_STRING.length(), border);
        System.out.print(TRANSCRIPT_HEADER);
        UI.DrawInlineBar(PANEL_LENGTH - INDENT_STRING.length() - TRANSCRIPT_HEADER.length(), border);
        System.out.println();
        student.getTranscript().forEach((course, grade) ->
        {
            System.out.print(INDENT_STRING);
            System.out.print(course.getName());
            System.out.print(sep);
            System.out.print("Grade: " + grade);
            System.out.print(sep);
            System.out.print(course.getCredits() + CREDIT_HOURS);
            System.out.println();
        });
        UI.DrawBar(PANEL_LENGTH, border);
        System.out.println();
    }

    void DisplaySchedule() {
        char sep = '/';

        UI.DrawInlineBar(INDENT_STRING.length(), '=');
        System.out.print(SCHEDULE_HEADER);
        UI.DrawInlineBar(PANEL_LENGTH - INDENT_STRING.length() - SCHEDULE_HEADER.length(), '=');
        System.out.println();

        int index = 1;

        for (CourseOffering offering : student.getSchedule()) {
            System.out.print(INDENT_STRING);
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

        if (index == 1) {  // i.e. Student's schedule is empty
            System.out.println();
            System.out.print(INDENT_STRING);
            System.out.println("[ You currently have no courses in your schedule. ]");
            System.out.println();
        }

        UI.DrawBar(PANEL_LENGTH, '=');
        System.out.println();
    }

    void RemoveFromSchedule(CourseOffering _offering) throws Exception {
        schedule.remove(_offering);
        SceneManager.Next(new InfoScene(student, COURSE_REMOVED_MSG));
    }

    void WaitForInput() throws Exception {
        Scanner scn = new Scanner(System.in);

        System.out.print(INDENT_STRING);

        if (schedule.isEmpty()) {
            System.out.print(RETURN_MSG);
            scn.nextLine();
            SceneManager.Next(new DashboardScene(student));
        } else {
            System.out.print(MENU_TEXT);

            try {
                int option = scn.nextInt();

                if (option == 0)  // Return to Dashboard
                    SceneManager.Next(new DashboardScene(student));
                else if (option < schedule.size() + 1)  // Valid offering selected
                    RemoveFromSchedule(schedule.get(option - 1));
                else  // Invalid input -> Retry()
                    Retry();

            } catch (InputMismatchException e) {
                Retry();
            }
        }
    }

    void Retry() throws Exception {
        System.out.println(INVALID_OPTION_ERROR);
        WaitForInput();
    }
}
