import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
        } else {
            System.out.println("이미 존재하는 아이디입니다.");
            // 회원가입 정보 작성 화면으로 이동하는 코드 필요
        }
    }

    public boolean isUserValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void removeUser(String username) {
        users.remove(username);
    }
}
