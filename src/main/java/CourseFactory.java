import java.util.Map;
import java.util.HashMap;
import java.io.*;

import org.json.*;

public class CourseFactory {
    public static Map<Integer, Course> CreateCourseMapFromJSON(InputStream _inputStream)
        throws Exception
    {
        Map<Integer, Course> map = new HashMap<>();

        var reader = new BufferedReader(new InputStreamReader(_inputStream));
        String line;
        var responseStrBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseStrBuilder.append(line);
        }
        _inputStream.close();

        var json = new JSONObject(responseStrBuilder.toString());

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
