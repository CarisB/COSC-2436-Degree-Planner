package View;

import Model.Student;

public class InfoScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";

    Student student;

    public InfoScene(Student _student) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);

        student = _student;
    }

    public void Init() throws Exception {
        DrawBar(100, '*');
        System.out.println(student.getName());
        System.out.println(student.getDegree().getName());
        DrawBar(100, '*');
        System.in.read();
    }

    void DrawBar(int _size, char _char) {
        System.out.println(String.valueOf(_char).repeat(_size));
    }
}
