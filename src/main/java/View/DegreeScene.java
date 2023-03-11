package View;

import Global.SceneManager;
import Model.Student;
import Model.Degree;

public class DegreeScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String INDENT_STRING = "    ";
    final int PANEL_LENGTH = 100;

    Student student;

    public DegreeScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
    }

    public void Init() throws Exception {
        Degree degree = student.getDegree();
        DrawBar(PANEL_LENGTH, '=');
        System.out.print(INDENT_STRING);
        System.out.println(degree.getName());
        DrawBar(PANEL_LENGTH, '=');
        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }

    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }

    void DrawInlineBar(int _size, char _char) {
        System.out.print(String.valueOf(_char).repeat(_size));
    }
}
