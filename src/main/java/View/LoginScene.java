package View;

import Global.Maps;
import Global.SceneManager;
import Model.Student;
import java.util.Scanner;

public class LoginScene implements IScene {
    final String LOGIN_PROMPT = "Please log in using your student ID [*****]: ";
    final String INVALID_INPUT_ERROR = "Please enter a valid ID consisting of 5 numbers.";
    final String RETRY_MSG = "Press ENTER to continue.";

    public void Init() throws Exception {
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

    void Retry(String _s) throws Exception {
        System.out.println(_s);
        System.out.print(RETRY_MSG);
        System.in.read();
        SceneManager.Next(new LoginScene());
    }
}
