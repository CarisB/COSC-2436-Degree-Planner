import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<Integer, Course> courseMap = CourseFactory.CreateCourseMapFromJSON("courses.json");

        courseMap.forEach((id, course) -> {
            System.out.println(course.getId());
            System.out.println(course.getName());
            System.out.println(course.getDescription());
            System.out.println(course.getCredits());
        });
    }
}