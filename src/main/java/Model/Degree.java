package Model;

public class Degree {
    int id;
    String name;
    int coreCredits;
    int majorReqCredits;
    int electiveCredits;
    Course[] core;
    Course[] majorReq;
    Course[] elective;

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCoreCredits() { return coreCredits; }
    public int getMajorReqCredits() { return majorReqCredits; }
    public int getElectiveCredits() { return electiveCredits; }
    public Course[] getCore() { return core; }
    public Course[] getMajorReq() { return majorReq; }
    public Course[] getElective() { return elective; }

    public Degree(int _id,
                  String _name,
                  int _coreCredits,
                  int _majorReqCredits,
                  int _electiveCredits,
                  Course[] _core,
                  Course[] _majorReq,
                  Course[] _elective) {
        id = _id;
        name = _name;
        coreCredits = _coreCredits;
        majorReqCredits = _majorReqCredits;
        electiveCredits = _electiveCredits;
        core = _core;
        majorReq = _majorReq;
        elective = _elective;
    }
}
