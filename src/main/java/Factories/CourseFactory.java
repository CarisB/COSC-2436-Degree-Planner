package Factories;

import org.json.*;
import java.util.Map;

public class CourseFactory {
    public static Map<Integer, Course> CreateCourseMapFromJSON(string _path) {
        Map<Integer, Course> map = new Map<Integer, Course>();

        JSONObject json = new JSONObject(_path);

        return map;
    }
}
