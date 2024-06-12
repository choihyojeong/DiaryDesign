import java.util.Scanner;

public class Weather {
    private Scanner scanner;

    public Weather() {
        this.scanner = new Scanner(System.in);
    }

    public String selectWeather() {
        System.out.println("날씨 선택:");
        System.out.println("1. 맑음");
        System.out.println("2. 구름");
        System.out.println("3. 비");
        System.out.println("4. 눈");
        System.out.println("5. 불러오기");
        System.out.print("입력: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return "맑음";
            case 2:
                return "구름";
            case 3:
                return "비";
            case 4:
                return "눈";
            case 5:
            	return " ";
            	// 불러오기 구현 필요
            default:
                System.out.println("옵션 오류입니다. 날씨는 공백으로 입력됩니다.");
                return " ";
        }
    }
    
    public String defaultWeather() {
    	return " ";
    }
}
