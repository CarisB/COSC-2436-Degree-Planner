public class Main {
    public static void main(String[] args) {
        Course course = new Course(
                10001,
                "Test Course",
                "This is a test course.",
                4,
                null
        );

        System.out.println(course.getId());
        System.out.println(course.getName());
        System.out.println(course.getDescription());
        System.out.println(course.getCredits());
    }
}