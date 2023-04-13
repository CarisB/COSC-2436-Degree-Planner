package Model;

import java.util.Map;
import java.util.ArrayList;

public class Student {
    int id;
    String name;
    Degree degree;
    Map<Course, Integer> transcript;
    ArrayList<CourseOffering> schedule;

    public int getId() { return id; }
    public String getName() { return name; }
    public Degree getDegree() { return degree; }
    public Map<Course, Integer> getTranscript() { return transcript; }

    public Student(int _id,
                  String _name,
                  Degree _degree,
                  Map<Course, Integer> _transcript) {
        id = _id;
        name = _name;
        degree = _degree;
        transcript = _transcript;
        schedule = new ArrayList<>();
    }
}
