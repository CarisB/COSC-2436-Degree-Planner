package Classes;

public class Course {
    int id;
    String name;
    String description;
    int credits;
    int[] prereqs;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getCredits() { return credits; }
    public int[] getPrereqs() { return prereqs; }

    public Course(int _id,
                  String _name,
                  String _description,
                  int _credits,
                  int[] _prereqs) {
        id = _id;
        name = _name;
        description = _description;
        credits = _credits;
        prereqs = _prereqs;
    }
}
