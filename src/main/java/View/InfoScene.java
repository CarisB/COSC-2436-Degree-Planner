package View;

import Global.SceneManager;
import Model.Student;

public class InfoScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String INDENT_STRING = "    ";
    final String TRANSCRIPT_HEADER = "TRANSCRIPT";
    final String RETURN_MSG = "[ Press ENTER to return to the Dashboard ]";
    final int PANEL_LENGTH = 100;

    Student student;

    public InfoScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
    }

    public void Init() throws Exception {
        DrawBar(PANEL_LENGTH, '*');
        System.out.println(INDENT_STRING + student.getName());
        System.out.println(INDENT_STRING + student.getDegree().getName());
        DrawBar(PANEL_LENGTH, '*');
        DisplayTranscript();
        System.out.println(RETURN_MSG);

        System.in.read();
        SceneManager.Next(new DashboardScene(student));
    }

    void DisplayTranscript() {
        DrawInlineBar(INDENT_STRING.length(), '=');
        System.out.print(TRANSCRIPT_HEADER);
        DrawInlineBar(PANEL_LENGTH - INDENT_STRING.length() - TRANSCRIPT_HEADER.length(), '=');
        System.out.println();
        student.getTranscript().forEach((course, grade) ->
        {
            System.out.print(INDENT_STRING);
            System.out.print(course.getName());
            System.out.print(INDENT_STRING);
            System.out.print(grade);
            System.out.println();
        });
        DrawBar(PANEL_LENGTH, '=');
    }

    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }

    void DrawInlineBar(int _size, char _char) {
        System.out.print(String.valueOf(_char).repeat(_size));
    }
}
