import java.util.Scanner;

public class Weather {
    private Scanner scanner;

    public Weather() {
        this.scanner = new Scanner(System.in);
    }

    public String selectWeather() {
        System.out.println("Select weather:");
        System.out.println("1. Sunny");
        System.out.println("2. Cloudy");
        System.out.println("3. Rainy");
        System.out.println("4. Snowy");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                return "Sunny";
            case 2:
                return "Cloudy";
            case 3:
                return "Rainy";
            case 4:
                return "Snowy";
            default:
                System.out.println("Invalid option. Defaulting to Sunny.");
                return "Sunny";
        }
    }
}
