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
import UI.*;

import java.io.InputStream;

public class Main {
    static final IScene INITIAL_SCENE = new LoginScene();

    public static void main(String[] args) throws Exception {
        InputStream stream;
        stream = Main.class.getClassLoader().getResourceAsStream("courses.json");
        var courseMap = Maps.SetCourseMap(DataCreator.CreateCourseMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream("majors.json");
        var majorMap = Maps.SetMajorMap(DataCreator.CreateMajorMapFromJSON(stream));
        stream = Main.class.getClassLoader().getResourceAsStream("students.json");
        var studentMap = Maps.SetStudentMap(DataCreator.CreateStudentMapFromJSON(stream));

        SceneManager.Next(INITIAL_SCENE);
    }
}