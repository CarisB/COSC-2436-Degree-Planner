import java.util.Map;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream("courses.json");
        Map<Integer, Course> courseMap = CourseFactory.CreateCourseMapFromJSON(stream);

        courseMap.forEach((id, course) -> {
            System.out.println(course.getId());
            System.out.println(course.getName());
            System.out.println(course.getDescription());
            System.out.println(course.getCredits());
            for (int i = 0; i < course.getPrereqs().length; i++) {
                System.out.println("Prereq: " + course.getPrereqs()[i]);
            }
            System.out.println();
        });
    }
}