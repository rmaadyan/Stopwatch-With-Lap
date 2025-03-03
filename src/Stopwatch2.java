import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stopwatch2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long t = 0;
        long startTime = 0;
        boolean isRunning = false;
        List<Long> lapTimes = new ArrayList<>();

        while (true) {
            System.out.print("Enter command (start, stop, lap, show, exit): ");
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "start":
                    if (!isRunning) {
                        startTime = System.currentTimeMillis();
                        isRunning = true;
                        System.out.println("Stopwatch started.");
                    }
                    break;
                case "stop":
                    if (isRunning) {
                        t += System.currentTimeMillis() - startTime;
                        isRunning = false;
                        System.out.println("Stopwatch stopped. Total elapsed time: " + (t / 1000) + " seconds.");
                    }
                    break;
                case "lap":
                    if (isRunning) {
                        long lapTime = System.currentTimeMillis() - startTime + t;
                        lapTimes.add(lapTime);
                        System.out.println("Lap time recorded: " + (lapTime / 1000) + " seconds.");
                    }
                    break;
                case "show":
                    System.out.println("Lap Times:");
                    for (int i = 0; i < lapTimes.size(); i++) {
                        System.out.println("Lap " + (i + 1) + ": " + (lapTimes.get(i) / 1000) + " seconds.");
                    }
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }
}

