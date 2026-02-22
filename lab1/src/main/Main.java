import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter array size: ");
        AttendanceTrackSystem system = new AttendanceTrackSystem(Integer.parseInt(scanner.nextLine()));
        system.Fill();
        System.out.println("-".repeat(21));
        system.AddAttendance();
        system.Show();
        System.out.println("-".repeat(21));
        system.FindByDate();
    }
}