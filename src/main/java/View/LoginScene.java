package View;

import Global.Maps;
import Global.SceneManager;
import Model.Student;
import Helpers.UI;

import java.util.Scanner;

public class LoginScene implements IScene {
    final String LOGIN_PROMPT = "Please log in using your student ID [*****] (e.g. 10000): ";
    final String INVALID_INPUT_ERROR = "Please enter a valid ID consisting of 5 numbers.";

    String message;

    public LoginScene(String _message) {
        message = _message;
    }

    public void Init() {
        DisplayMessage();
        WaitForInput();
    }

    void DisplayMessage() {
        if (message == null)
            return;

        UI.DrawBar(message.length(), '-');
        System.out.println(message);
        UI.DrawBar(message.length(), '-');
    }

    void WaitForInput() {
        Scanner scn = new Scanner(System.in);

        System.out.print(LOGIN_PROMPT);

        try {   // Validate that input is an int
            int id = scn.nextInt();

            try {   // Validate that ID exists in Student map
                Student student = Maps.GetStudentById(id);
                SceneManager.Next(new DashboardScene(student)); // Go to Dashboard
            }
            catch (Exception e) {   // Student cannot be found
                Retry(e.getMessage());
            }
        } catch (Exception e) { // Input is not an int
            Retry(INVALID_INPUT_ERROR);
        }
    }

    void Retry(String _s) {
        SceneManager.Next(new LoginScene(_s));
    }
}
