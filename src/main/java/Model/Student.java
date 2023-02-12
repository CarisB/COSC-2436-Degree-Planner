package Model;

import java.util.Map;

public class Student {
    int id;
    String name;
    Degree major;
    Map<Course, Integer> transcript;

    public int getId() { return id; }
    public String getName() { return name; }
    public Degree getMajor() { return major; }
    public Map<Course, Integer> getTranscript() { return transcript; }

    public Student(int _id,
                  String _name,
                  Degree _major,
                  Map<Course, Integer> _transcript) {
        id = _id;
        name = _name;
        major = _major;
        transcript = _transcript;
    }
}
