import java.util.List;

public class Major {
    String name;
    List<Course> core;
    List<Course> elective;

    public String getName() { return name; }
    public List<Course> getCore() { return core; }
    public List<Course> getElective() { return elective; }
}
