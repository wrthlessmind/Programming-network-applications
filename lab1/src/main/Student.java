import java.util.TreeSet;

public class Student {
    private final String name;
    private final TreeSet<String> visits;

    public Student(String name) {
        this.name = name;
        visits = new TreeSet<>();
    }

    public String getName() { return name; }

    public void addDate(String date) { visits.add(date); }
    public boolean hasDate(String date) { return visits.contains(date); }
    public void showDates() {
        for (String date : visits) {
            System.out.println("\t" + date);
        }
    }
}