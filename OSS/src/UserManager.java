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
            System.out.println("User already exists.");
        }
    }

    public boolean isUserValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void removeUser(String username) {
        users.remove(username);
    }
}
