import java.nio.file.Files;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;

import org.json.*;

public class CourseFactory {
    public static Map<Integer, Course> CreateCourseMapFromJSON(String _path)
        throws Exception
    {
        Map<Integer, Course> map = new HashMap<>();

        Path path = Path.of(_path);
        var json = new JSONObject(Files.readString(path));

        var courses = json.getJSONArray("courses");
        for (int i = 0; i < courses.length(); i++) {
            var course = courses.getJSONObject(i);

            int id = course.getInt("id");
            String name = course.getString("name");
            String description = course.getString("description");
            int credits = course.getInt("credits");

            var arr = course.getJSONArray("prereqs");
            int[] prereqs = new int[arr.length()];

            for (int j = 0; j < arr.length(); j++) {
                prereqs[j] = arr.getInt(j);
            }

            map.put(id, new Course(id, name, description, credits, prereqs));
        }

        return map;
    }
}
