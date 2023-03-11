package Model;

public class Degree {
    int id;
    String name;
    Course[] core;
    Course[] majorReq;
    Course[] elective;

    public int getId() { return id; }
    public String getName() { return name; }
    public Course[] getCore() { return core; }
    public Course[] getMajorReq() { return majorReq; }
    public Course[] getElective() { return elective; }

    public Degree(int _id,
                  String _name,
                  Course[] _core,
                  Course[] _majorReq,
                  Course[] _elective) {
        id = _id;
        name = _name;
        core = _core;
        majorReq = _majorReq;
        elective = _elective;
    }
}
