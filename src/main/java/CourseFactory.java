import org.json.*;
import java.util.Map;
import java.util.HashMap;

public class CourseFactory {
    public static Map<Integer, Course> CreateCourseMapFromJSON(String _path) {
        Map<Integer, Course> map = new HashMap<Integer, Course>();

        JSONObject json = new JSONObject(_path);

        return map;
    }
}
