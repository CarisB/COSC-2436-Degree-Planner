import java.util.Map;
import java.util.HashMap;
import java.io.*;

import org.json.*;

public class DataCreator {
    // JSON data field names
    static final String COURSE_ARRAY = "courses";
    static final String COURSE_ID = "id";
    static final String COURSE_NAME = "name";
    static final String COURSE_DESCRIPTION = "description";
    static final String COURSE_CREDITS = "credits";
    static final String COURSE_PREREQS = "prereqs";

    static final String MAJOR_ARRAY = "majors";
    static final String MAJOR_ID = "id";
    static final String MAJOR_NAME = "name";
    static final String MAJOR_CORE = "core";
    static final String MAJOR_ELECTIVE = "elective";

    static final String STUDENT_ARRAY = "students";
    static final String STUDENT_ID = "id";
    static final String STUDENT_NAME = "name";
    static final String STUDENT_MAJOR_ID = "majorId";
    static final String STUDENT_TRANSCRIPT = "transcript";
    static final String STUDENT_TRANSCRIPT_COURSE_ID = "id";
    static final String STUDENT_TRANSCRIPT_COURSE_GRADE = "grade";

    static String ConvertInputStreamToString(InputStream _stream)
            throws Exception
    {
        var reader = new BufferedReader(new InputStreamReader(_stream));
        var strBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            strBuilder.append(line);
        }

        _stream.close();
        return strBuilder.toString();
    }

    public static Map<Integer, Course> CreateCourseMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateCourseMapFromJSON(json);
    }

    public static Map<Integer, Course> CreateCourseMapFromJSON(String _json)
            throws Exception
    {
        Map<Integer, Course> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);
        var courses = jsonObj.getJSONArray(COURSE_ARRAY);

        for (int i = 0; i < courses.length(); i++) {
            var course = courses.getJSONObject(i);

            int id = course.getInt(COURSE_ID);
            String name = course.getString(COURSE_NAME);
            String description = course.getString(COURSE_DESCRIPTION);
            int credits = course.getInt(COURSE_CREDITS);

            var arr = course.getJSONArray(COURSE_PREREQS);
            int[] prereqs = new int[arr.length()];

            for (int j = 0; j < arr.length(); j++) {
                prereqs[j] = arr.getInt(j);
            }

            map.put(id, new Course(id, name, description, credits, prereqs));
        }

        return map;
    }

    public static Map<Integer, Major> CreateMajorMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateMajorMapFromJSON(json);
    }

    public static Map<Integer, Major> CreateMajorMapFromJSON(String _json)
            throws Exception
    {
        Map<Integer, Major> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);
        var majors = jsonObj.getJSONArray(MAJOR_ARRAY);

        for (int i = 0; i < majors.length(); i++) {
            var major = majors.getJSONObject(i);

            int id = major.getInt(MAJOR_ID);
            String name = major.getString(MAJOR_NAME);

            var coreArr = major.getJSONArray(MAJOR_CORE);
            Course[] core = new Course[coreArr.length()];

            for (int j = 0; j < coreArr.length(); j++) {
                int courseId = coreArr.getInt(j);
                core[j] = Maps.GetCourseById(courseId);
            }

            var electiveArr = major.getJSONArray(MAJOR_ELECTIVE);
            Course[] elective = new Course[electiveArr.length()];

            for (int j = 0; j < electiveArr.length(); j++) {
                int courseId = electiveArr.getInt(j);
                elective[j] = Maps.GetCourseById(courseId);
            }

            map.put(id, new Major(id, name, core, elective));
        }

        return map;
    }

    public static Map<Integer, Student> CreateStudentMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateStudentMapFromJSON(json);
    }

    public static Map<Integer, Student> CreateStudentMapFromJSON(String _json)
            throws Exception
    {
        Map<Integer, Student> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);
        var students = jsonObj.getJSONArray(STUDENT_ARRAY);

        for (int i = 0; i < students.length(); i++) {
            var student = students.getJSONObject(i);

            int id = student.getInt(STUDENT_ID);
            String name = student.getString(STUDENT_NAME);
            int majorId = student.getInt(STUDENT_MAJOR_ID);

            var arr = student.getJSONArray(STUDENT_TRANSCRIPT);
            Map<Course, Integer> transcript = new HashMap<>();

            for (int j = 0; j < arr.length(); j++) {
                var course = arr.getJSONObject(j);
                int courseId = course.getInt(STUDENT_TRANSCRIPT_COURSE_ID);
                int courseGrade = course.getInt(STUDENT_TRANSCRIPT_COURSE_GRADE);

                transcript.put(Maps.GetCourseById(courseId), courseGrade);
            }

            map.put(id, new Student(id, name, Maps.GetMajorById(majorId), transcript));
        }

        return map;
    }
}
