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
        System.out.println(String.format(WELCOME_MSG, student.getName()));

        int barLength = INFO_OPTION.length()
                + DEGREE_OPTION.length()
                + COURSE_LIST_OPTION.length()
                + (OPTION_SEPARATOR.length() * 2);
        for (int i = 0; i < barLength; i++) { System.out.print("="); }

        System.out.println();
        System.out.print(INFO_OPTION);
        System.out.print(OPTION_SEPARATOR);
        System.out.print(DEGREE_OPTION);
        System.out.print(OPTION_SEPARATOR);
        System.out.print(COURSE_LIST_OPTION);
        System.out.println();
        System.out.print(INPUT_PROMPT);
    }

    void WaitForInput() throws Exception {
        Scanner scn = new Scanner(System.in);
        int option = scn.nextInt();

        switch (option) {
            case 1: SceneManager.Next(new InfoScene()); break;
            case 2: SceneManager.Next(new DegreeScene()); break;
            case 3: SceneManager.Next(new CourseListScene()); break;
            default: Retry(); break;
        }
    }

    void Retry() throws Exception {
        System.out.println(INVALID_OPTION_ERROR);
        System.out.print(RETRY_MSG);
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }
}
