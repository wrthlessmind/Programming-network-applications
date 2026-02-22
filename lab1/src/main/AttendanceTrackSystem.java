import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

class AttendanceTrackSystem
{
    private final static Logger logger = LogManager.getLogger(AttendanceTrackSystem.class);
    private final Student[] students;
    private final int arrSize;

    private String DateInput(Scanner scanner) {
        System.out.println("Enter day(1-31): ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter month(1-12): ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());
        return day + "." + month + "." + year;
    }

    public AttendanceTrackSystem(int size) {
        arrSize = size;
        students = new Student[size];
    }

    public void Fill() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < arrSize; i++) {
            System.out.println("Enter student's name: ");
            students[i] = new Student(scanner.nextLine());
        }
    }
    public void AddAttendance() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        System.out.println("Enter student's name: ");
        String name = scanner.nextLine();
        for (int i = 0; i < arrSize; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                found = true;
                do {
                    String date = DateInput(scanner);
                    students[i].addDate(date);
                    logger.info("Record added: student '{}', date '{}'.", name, date);
                    System.out.println("continue? (yes - 1, no - 0)");
                } while (Integer.parseInt(scanner.nextLine()) != 0);
                break;
            }
        }
        if (!found) logger.warn("Student '{}' not found.", name);
    }
    public void FindByDate() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        System.out.println("Enter student's name: ");
        String name = scanner.nextLine();
        for (int i = 0; i < arrSize; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                found = true;
                String date = DateInput(scanner);
                if (students[i].hasDate(date)) {
                    logger.info("Student '{}' was present on {}.", students[i].getName(), date);
                } else {
                    logger.warn("Student '{}' was absent on {}.", students[i].getName(), date);
                }
                break;
            }
        }
        if (!found) logger.warn("Student '{}' not found.", name);
    }
    public void Show() {
        System.out.println("\nStudents Attendance:");
        for (int i = 0; i < arrSize; i++) {
            System.out.println(students[i].getName() + "\n\tVisits:");
            students[i].showDates();
        }
    }
}
