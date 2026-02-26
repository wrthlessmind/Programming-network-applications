import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class Student {
    private final String name;
    private final Set<String> visits;

    public Student(String name) {
        this.name = name;
        visits = new TreeSet<>(Comparator.comparing(date -> {
            String[] a = date.split("\\.");
            return a[2] + a[1] + a[0];
        }));
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