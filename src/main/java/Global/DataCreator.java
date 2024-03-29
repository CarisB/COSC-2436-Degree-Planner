package Global;

import Model.*;
import Helpers.Enums.CourseFormat;

import java.util.List;
import java.util.ArrayList;
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

    static final String DEGREE_ARRAY = "degrees";
    static final String DEGREE_ID = "id";
    static final String DEGREE_NAME = "name";
    static final String DEGREE_CORE_CREDITS = "coreCredits";
    static final String DEGREE_MAJOR_REQ_CREDITS = "majorReqCredits";
    static final String DEGREE_ELECTIVE_CREDITS = "electiveCredits";
    static final String DEGREE_CORE = "core";
    static final String DEGREE_MAJOR_REQ = "majorReq";
    static final String DEGREE_ELECTIVE = "elective";

    static final String STUDENT_ARRAY = "students";
    static final String STUDENT_ID = "id";
    static final String STUDENT_NAME = "name";
    static final String STUDENT_DEGREE_ID = "degreeId";
    static final String STUDENT_TRANSCRIPT = "transcript";
    static final String STUDENT_TRANSCRIPT_COURSE_ID = "id";
    static final String STUDENT_TRANSCRIPT_COURSE_GRADE = "grade";

    static final String COURSE_OFFERING_ARRAY = "courseOfferings";
    static final String COURSE_OFFERING_COURSE_ID = "courseID";
    static final String COURSE_OFFERING_DATE = "date";
    static final String COURSE_OFFERING_TIME = "time";
    static final String COURSE_OFFERING_INSTRUCTOR = "instructor";
    static final String COURSE_OFFERING_FORMAT = "format";

    public static Map<Integer, Course> CreateCourseMapFromJSON(String _json) {
        Map<Integer, Course> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);  // Creates the obj to be parsed using the JSON String
        var courses = jsonObj.getJSONArray(COURSE_ARRAY);  // The parent array of objects

        for (int i = 0; i < courses.length(); i++) {
            var course = courses.getJSONObject(i);

            int id = course.getInt(COURSE_ID);
            String name = course.getString(COURSE_NAME);
            String description = course.getString(COURSE_DESCRIPTION);
            int credits = course.getInt(COURSE_CREDITS);

            var arr = course.getJSONArray(COURSE_PREREQS);  // Array of prereq course IDs
            int[] prereqs = new int[arr.length()];

            for (int j = 0; j < arr.length(); j++) {  // Copy contents of arr to prereqs[]
                prereqs[j] = arr.getInt(j);
            }

            map.put(id, new Course(id, name, description, credits, prereqs));
        }

        return map;
    }

    public static Map<Integer, Degree> CreateDegreeMapFromJSON(String _json)
            throws Exception
    {
        Map<Integer, Degree> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);  // Creates the obj to be parsed using the JSON String
        var degrees = jsonObj.getJSONArray(DEGREE_ARRAY);  // The parent array of objects

        for (int i = 0; i < degrees.length(); i++) {
            var degree = degrees.getJSONObject(i);

            int id = degree.getInt(DEGREE_ID);
            String name = degree.getString(DEGREE_NAME);
            int coreCredits = degree.getInt(DEGREE_CORE_CREDITS);
            int majorReqCredits = degree.getInt(DEGREE_MAJOR_REQ_CREDITS);
            int electiveCredits = degree.getInt(DEGREE_ELECTIVE_CREDITS);

            JSONArray arr;

            arr = degree.getJSONArray(DEGREE_CORE);  // Array of core course IDs
            Course[] core = new Course[arr.length()];

            for (int j = 0; j < arr.length(); j++) {  // Copy contents of arr to core[]
                int courseId = arr.getInt(j);
                core[j] = Maps.GetCourseById(courseId);
            }

            arr = degree.getJSONArray(DEGREE_MAJOR_REQ);  // Array of core course IDs
            Course[] majorReq = new Course[arr.length()];

            for (int j = 0; j < arr.length(); j++) {  // Copy contents of arr to majorReq[]
                int courseId = arr.getInt(j);
                majorReq[j] = Maps.GetCourseById(courseId);
            }

            arr = degree.getJSONArray(DEGREE_ELECTIVE);  // Array of elective course IDs
            Course[] elective = new Course[arr.length()];

            for (int j = 0; j < arr.length(); j++) {  // Copy contents of arr to elective[]
                int courseId = arr.getInt(j);
                elective[j] = Maps.GetCourseById(courseId);
            }

            map.put(id, new Degree(
                    id,
                    name,
                    coreCredits,
                    majorReqCredits,
                    electiveCredits,
                    core,
                    majorReq,
                    elective));
        }

        return map;
    }

    public static Map<Integer, Student> CreateStudentMapFromJSON(String _json)
            throws Exception
    {
        Map<Integer, Student> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);  // Creates the obj to be parsed using the JSON String
        var students = jsonObj.getJSONArray(STUDENT_ARRAY);  // The parent array of objects

        for (int i = 0; i < students.length(); i++) {
            var student = students.getJSONObject(i);

            int id = student.getInt(STUDENT_ID);
            String name = student.getString(STUDENT_NAME);
            int degreeId = student.getInt(STUDENT_DEGREE_ID);

            var arr = student.getJSONArray(STUDENT_TRANSCRIPT);
            Map<Course, Integer> transcript = new HashMap<>();

            for (int j = 0; j < arr.length(); j++) {  // Populates transcript map (<course, grade>)
                var course = arr.getJSONObject(j);
                int courseId = course.getInt(STUDENT_TRANSCRIPT_COURSE_ID);
                int courseGrade = course.getInt(STUDENT_TRANSCRIPT_COURSE_GRADE);

                transcript.put(Maps.GetCourseById(courseId), courseGrade);
            }

            map.put(id, new Student(id, name, Maps.GetDegreeById(degreeId), transcript));
        }

        return map;
    }

    public static Map<Integer, List<CourseOffering>> CreateCourseOfferingMapFromJSON(String _json)
            throws Exception
    {
        // Map has ArrayList as value, so that course offerings can have duplicate keys
        Map<Integer, List<CourseOffering>> map = new HashMap<>();

        var jsonObj = new JSONObject(_json);  // Creates the obj to be parsed using the JSON String
        var offerings = jsonObj.getJSONArray(COURSE_OFFERING_ARRAY);  // The parent array of objects

        for (int i = 0; i < offerings.length(); i++) {
            var offering = offerings.getJSONObject(i);

            int courseID = offering.getInt(COURSE_OFFERING_COURSE_ID);
            String date = offering.getString(COURSE_OFFERING_DATE);
            String time = offering.getString(COURSE_OFFERING_TIME);
            String instructor = offering.getString(COURSE_OFFERING_INSTRUCTOR);
            CourseFormat format = CourseFormat.values()[offering.getInt(COURSE_OFFERING_FORMAT)];  // Convert int to CourseFormat

            CourseOffering entry = new CourseOffering(Maps.GetCourseById(courseID), date, time, instructor, format);
            map.computeIfAbsent(courseID, k -> new ArrayList<>()).add(entry);  // Add entry to map, but create a new ArrayList first if it doesn't exist
        }

        return map;
    }

    // Takes an InputStream object and returns a JSON String to be parsed
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

    // Method overloads for ease of use

    public static Map<Integer, Course> CreateCourseMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateCourseMapFromJSON(json);
    }

    public static Map<Integer, Degree> CreateDegreeMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateDegreeMapFromJSON(json);
    }

    public static Map<Integer, Student> CreateStudentMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateStudentMapFromJSON(json);
    }

    public static Map<Integer, List<CourseOffering>> CreateCourseOfferingMapFromJSON(InputStream _stream)
            throws Exception
    {
        String json = ConvertInputStreamToString(_stream);
        return CreateCourseOfferingMapFromJSON(json);
    }
}
