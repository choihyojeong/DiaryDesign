import java.time.LocalDate;
import java.util.Scanner;

public class MainPage {
    private UserManager userManager;
    private Auth auth;
    private Diary diary;
    private Weather weather;
    private DateListManager dateListManager;
    private Scanner scanner;

    public MainPage() {
        scanner = new Scanner(System.in);
        userManager = new UserManager();
        auth = new Auth(userManager);
        diary = new Diary();
        weather = new Weather();
        dateListManager = new DateListManager();
    }

    public void run() {
        while (true) {
            System.out.println("\nDiary Application");
            if (auth.isAuthenticated()) {
                System.out.println("1. Add Entry");
                System.out.println("2. View Entry");
                System.out.println("3. Update Entry");
                System.out.println("4. Delete Entry");
                System.out.println("5. View All Entries");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        addEntry();
                        break;
                    case 2:
                        viewEntry();
                        break;
                    case 3:
                        updateEntry();
                        break;
                    case 4:
                        deleteEntry();
                        break;
                    case 5:
                        viewAllEntries();
                        break;
                    case 6:
                        auth.logout();
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } else {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private void addEntry() {
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        String weatherInfo = weather.selectWeather();
        diary.addEntry(new DiaryEntry(date, title, content, weatherInfo));
        dateListManager.addDate(date);
    }

    private void viewEntry() {
        System.out.print("Enter date (yyyy-mm-dd) to view: ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        DiaryEntry entry = diary.getEntry(date);
        if (entry != null) {
            System.out.println(entry);
            System.out.println("1. Update Entry");
            System.out.println("2. Delete Entry");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    updateEntry(entry);
                    break;
                case 2:
                    deleteEntry(entry);
                    break;
                case 3:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } else {
            System.out.println("Entry not found.");
        }
    }

    private void updateEntry(DiaryEntry entry) {
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new content: ");
        String content = scanner.nextLine();
        String weatherInfo = weather.selectWeather();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setWeather(weatherInfo);
    }

    private void updateEntry() {
        System.out.print("Enter date (yyyy-mm-dd) to update: ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        DiaryEntry entry = diary.getEntry(date);
        if (entry != null) {
            updateEntry(entry);
        } else {
            System.out.println("Entry not found.");
        }
    }

    private void deleteEntry(DiaryEntry entry) {
        diary.removeEntry(entry.getDate());
        dateListManager.removeDate(entry.getDate());
        System.out.println("Entry deleted.");
    }

    private void deleteEntry() {
        System.out.print("Enter date (yyyy-mm-dd) to delete: ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        diary.removeEntry(date);
        dateListManager.removeDate(date);
    }

    private void viewAllEntries() {
        System.out.println("All Entries:");
        for (DiaryEntry entry : diary.getAllEntries()) {
            System.out.println(entry);
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userManager.registerUser(username, password);
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (auth.login(username, password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
