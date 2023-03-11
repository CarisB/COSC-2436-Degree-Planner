package View;

import Global.SceneManager;
import Model.Student;
import java.util.Scanner;

public class DashboardScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String WELCOME_MSG = "Welcome back, %s";
    final String OPTION_SEPARATOR = "   ";
    final String INFO_OPTION = "1) View personal information";
    final String DEGREE_OPTION = "2) View degree information";
    final String COURSE_LIST_OPTION = "3) View available courses";
    final String LOGOUT_OPTION = "4) Log out";
    final String INPUT_PROMPT = "Please select an option: ";
    final String INVALID_OPTION_ERROR = "Please enter a valid option.";
    final String RETRY_MSG = "Press ENTER to continue.";

    Student student;

    public DashboardScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
    }

    public void Init() throws Exception {
        DisplayMenu();
        WaitForInput();
    }

    void DisplayMenu() {
        System.out.printf(WELCOME_MSG, student.getName());
        System.out.println();
        DrawBar();
        System.out.println(
                INFO_OPTION +
                OPTION_SEPARATOR +
                DEGREE_OPTION +
                OPTION_SEPARATOR +
                COURSE_LIST_OPTION +
                OPTION_SEPARATOR +
                LOGOUT_OPTION);
        DrawBar();
        System.out.print(INPUT_PROMPT);
    }

    void DrawBar() {
        int barLength =
                INFO_OPTION.length() +
                DEGREE_OPTION.length() +
                COURSE_LIST_OPTION.length() +
                LOGOUT_OPTION.length() +
                (OPTION_SEPARATOR.length() * 3);

        System.out.println("=".repeat(barLength));
    }

    void WaitForInput() throws Exception {
        Scanner scn = new Scanner(System.in);
        int option = scn.nextInt();

        // Load next Scene
        switch (option) {
            case 1 -> SceneManager.Next(new InfoScene(student));
            case 2 -> SceneManager.Next(new DegreeScene(student));
            case 3 -> SceneManager.Next(new CourseListScene());
            case 4 -> SceneManager.Next(new LoginScene());
            default -> Retry();
        }
    }

    void Retry() throws Exception {
        System.out.println(INVALID_OPTION_ERROR);
        System.out.print(RETRY_MSG);
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }
}
