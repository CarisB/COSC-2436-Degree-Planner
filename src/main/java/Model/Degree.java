package Model;

public class Degree {
    int id;
    String name;
    Course[] core;
    Course[] elective;

    public int getId() { return id; }
    public String getName() { return name; }
    public Course[] getCore() { return core; }
    public Course[] getElective() { return elective; }

    public Degree(int _id,
                  String _name,
                  Course[] _core,
                  Course[] _elective) {
        id = _id;
        name = _name;
        core = _core;
        elective = _elective;
    }
}