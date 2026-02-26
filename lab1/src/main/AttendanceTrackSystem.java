import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

class AttendanceTrackSystem {
    private final static Logger logger = LogManager.getLogger(AttendanceTrackSystem.class);
    private final Student[] students;
    private final int arrSize;

    private String DateInput(Scanner scanner) {
        try {
            System.out.println("Enter day(1-31): ");
            int day = Integer.parseInt(scanner.nextLine());
            if (day < 1 || day > 31) throw new IllegalArgumentException("Day must be between 1 and 31.");
            System.out.println("Enter month(1-12): ");
            int month = Integer.parseInt(scanner.nextLine());
            if (month < 1 || month > 12) throw new IllegalArgumentException("Month must be between 1 and 12.");
            System.out.println("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine());
            if (year <= 0) throw new IllegalArgumentException("Year must be > 0.");
            return String.format("%02d.%02d.%d", day, month, year);
        } catch (Exception e) {
            logger.error("Exception: " + e);
            return null;
        }
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
                    if (date != null) {
                        students[i].addDate(date);
                        logger.info("Record added: student '{}', date '{}'.", name, date);
                    } else {
                        logger.warn("Record NOT added: invalid input.");
                    }
                    System.out.println("continue? (yes - 1, no - 0)");
                } while (!scanner.nextLine().equals("0"));
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
                if (date != null) {
                    if (students[i].hasDate(date)) {
                        logger.info("Student '{}' was present on {}.", students[i].getName(), date);
                    } else {
                        logger.warn("Student '{}' was absent on {}.", students[i].getName(), date);
                    }
                } else {
                    logger.warn("Record NOT added: invalid input.");
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