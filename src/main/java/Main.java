/**
 * Degree Planner tool created for COSC 2436.
 * A student can log in using an ID and register degree-relevant classes to their schedule.
 *
 * @author  Christopher Baek
 * @version 1.0
 * @since   2023-02-08
 */

import Global.DataCreator;
import Global.Maps;
import Global.SceneManager;
import View.*;
import java.io.InputStream;

public class Main {
    static final String COURSES_PATH = "courses.json";
    static final String MAJORS_PATH = "majors.json";
    static final String STUDENTS_PATH = "students.json";
    static final IScene INITIAL_SCENE = new LoginScene();

    static void LoadData() throws Exception {
        InputStream stream;
        stream = Main.class.getClassLoader().getResourceAsStream(COURSES_PATH);
        Maps.SetCourseMap(DataCreator.CreateCourseMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream(MAJORS_PATH);
        Maps.SetMajorMap(DataCreator.CreateMajorMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream(STUDENTS_PATH);
        Maps.SetStudentMap(DataCreator.CreateStudentMapFromJSON(stream));
    }

    public static void main(String[] args) throws Exception {
        // Initial setup
        LoadData();
        SceneManager.Next(INITIAL_SCENE);

        // Main program loop
        while (true) {
            SceneManager.Init();
        }
    }
}