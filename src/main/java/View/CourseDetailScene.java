package View;

import Global.SceneManager;
import Model.Student;
import Model.Course;

public class CourseDetailScene implements IScene {
    final String NULL_STUDENT_ERROR = "Cannot have a null Student!";
    final String NULL_COURSE_ERROR = "Cannot have a null Course!";

    Student student;
    Course course;

    public CourseDetailScene(Student _student, Course _course) throws Exception {
        if (_student == null)
            throw new Exception(NULL_STUDENT_ERROR);
        if (_course == null)
            throw new Exception(NULL_COURSE_ERROR);

        student = _student;
        course = _course;
    }

    public void Init() throws Exception {

    }
}
