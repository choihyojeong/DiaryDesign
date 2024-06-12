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
            if (auth.isAuthenticated()) {
            	System.out.println("=============================");
                System.out.println("하루 한 줄의 메인 메뉴입니다");
                System.out.println("(원하는 작업의 번호를 입력해 주세요!)");
                System.out.println("=============================");
                System.out.println("1. 일기 등록");
                System.out.println("2. 일기 조회");
                System.out.println("3. 일기 수정");
                System.out.println("4. 일기 삭제");
                System.out.println("5. 날짜 조회");
                System.out.println("6. 로그아웃");
                System.out.print("입력: ");

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
                        viewAllEntriesDate();
                        break;
                    case 6:
                        auth.logout();
                        System.out.println("로그아웃.");
                        break;
                    default:
                        System.out.println("입력 옵션 오류입니다. 다시 입력해주세요!");
                }
            } else {
            	System.out.println("=============================");
                System.out.println("하루 한 줄의 시작 메뉴입니다");
                System.out.println("(원하는 작업의 번호를 입력해 주세요!)");
                System.out.println("=============================");
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("3. 프로그램 종료");
                System.out.print("입력: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("입력 옵션 오류입니다. 다시 입력해주세요!");
                }
            }
        }
    }

    private void addEntry() {
    	System.out.println("=============================");
    	System.out.println("일기 등록");
    	System.out.println("=============================");
        System.out.print("날짜 입력(yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("한 줄 일기: ");
        String content = scanner.nextLine();
        System.out.print("날씨 등록을 원한다면 1 입력: ");
        int weatherChoice = scanner.nextInt();
        String weatherInfo;
        if(weatherChoice == 1) {
        	weatherInfo = weather.selectWeather();
        }
        else weatherInfo = weather.defaultWeather();
        
        diary.addEntry(new DiaryEntry(date, title, content, weatherInfo));
        dateListManager.addDate(date);
    }

    private void viewEntry() {
    	System.out.println("=============================");
    	System.out.println("일기 조회");
    	System.out.println("=============================");
        System.out.print("날짜 입력(yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        DiaryEntry entry = diary.getEntry(date);
        if (entry != null) {
            System.out.println(entry);
            System.out.println("1. 일기 수정");
            System.out.println("2. 일기 삭제");
            System.out.println("3. 메인 메뉴로");
            System.out.print("입력: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.println("입력 옵션 오류입니다. 다시 입력해주세요!");
            }
        } else {
            System.out.println("해당 날짜에는 일기가 없습니다.");
        }
    }

    private void updateEntry(DiaryEntry entry) {
        System.out.print("새로운 제목: ");
        String title = scanner.nextLine();
        System.out.print("새로운 한 줄 일기: ");
        String content = scanner.nextLine();
        String weatherInfo = weather.selectWeather();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setWeather(weatherInfo);
    }

    private void updateEntry() {
    	System.out.println("=============================");
    	System.out.println("일기 수정");
    	System.out.println("=============================");
        System.out.print("날짜 입력(yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        DiaryEntry entry = diary.getEntry(date);
        if (entry != null) {
            updateEntry(entry);
        } else {
            System.out.println("Entry not found.");
        }
    }

    private void deleteEntry(DiaryEntry entry) {
    	System.out.println("=============================");
    	System.out.println("일기 삭제");
    	System.out.println("=============================");
    	System.out.println("정말 삭제하시겠습니까?\n삭제하려면 Y, 삭제를 취소하려면 N을 입력해주세요.");
    	System.out.print("입력: ");
    	String check = scanner.nextLine();
    	switch (check) {
        	case "Y":
        		diary.removeEntry(entry.getDate());
        		dateListManager.removeDate(entry.getDate());
        		System.out.println("삭제 완료.");
        		break;
        	case "N":
        		// Return to main menu
        		break;
        	default:
        		System.out.println("입력 옵션 오류! 메인메뉴로 이동합니다.");
    	}
    }

    private void deleteEntry() {
    	System.out.println("=============================");
    	System.out.println("일기 삭제");
    	System.out.println("=============================");
        System.out.print("날짜 입력(yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.println("정말 삭제하시겠습니까?\n삭제하려면 Y, 삭제를 취소하려면 N을 입력해주세요.");
    	System.out.print("입력: ");
    	String check = scanner.nextLine();
    	switch (check) {
        	case "Y":
        		diary.removeEntry(date);
                dateListManager.removeDate(date);
        		System.out.println("삭제 완료.");
        		break;
        	case "N":
        		// Return to main menu
        		break;
        	default:
        		System.out.println("입력 옵션 오류입니다. 다시 입력해주세요!");
        		// 삭제 메뉴로 돌아가기 구현 필요
    	}
    }

    private void viewAllEntriesDate() {
    	System.out.println("=============================");
    	System.out.println("날짜 조회");
    	System.out.println("(최근 1년간의 결과만 표시됩니다.)");
    	System.out.println("=============================");
        for (DiaryEntry entry : diary.getAllEntries()) {
            System.out.println(entry.getDate());
        }
    }

    private void registerUser() {
    	System.out.println("=============================");
    	System.out.println("회원가입");
    	System.out.println("=============================");
        System.out.print("아이디: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        userManager.registerUser(username, password);
    }

    private void loginUser() {
    	System.out.println("=============================");
    	System.out.println("로그인");
    	System.out.println("=============================");
        System.out.print("아이디: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        if (auth.login(username, password)) {
            System.out.println(username + "님 로그인 성공하셨습니다.");
        } else {
            System.out.println("아이디 혹은 비밀번호를 다시 확인해주세요.");
        }
    }
}
