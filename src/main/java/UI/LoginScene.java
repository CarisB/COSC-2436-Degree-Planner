package UI;

import Global.Maps;
import Classes.Student;
import java.util.Scanner;
import java.lang.Exception;

public class LoginScene implements IScene {
    final String LOGIN_PROMPT = "Please log in using your student ID [*****]: ";
    final String ERROR_MSG = "Could not find student with ID %i";

    public void Init()
            throws Exception
    {
        Scanner scn = new Scanner(System.in);

        System.out.println(LOGIN_PROMPT);
        int id = scn.nextInt();

        Student student;

        try {
            student = Maps.GetStudentById(id);
            System.out.println("Welcome, " + student.getName());
            scn.next();
        }
        catch (Exception e) {
            System.out.println(String.format(ERROR_MSG, id));
        }
    }
}
