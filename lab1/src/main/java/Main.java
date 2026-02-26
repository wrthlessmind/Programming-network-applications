import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class);

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter array size: ");
            int size = Integer.parseInt(scanner.nextLine());
            if (size <= 0) throw new IllegalArgumentException("Array size must be > 0.");
            AttendanceTrackSystem system = new AttendanceTrackSystem(size);
            system.Fill();
            System.out.println("-".repeat(21));
            system.AddAttendance();
            system.Show();
            System.out.println("-".repeat(21));
            system.FindByDate();
        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            logger.info("Program ended.");
        }
    }
}