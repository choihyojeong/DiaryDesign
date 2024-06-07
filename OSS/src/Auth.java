public class Auth {
    private UserManager userManager;
    private String loggedInUser;

    public Auth(UserManager userManager) {
        this.userManager = userManager;
        this.loggedInUser = null;
    }

    public boolean login(String username, String password) {
        if (userManager.isUserValid(username, password)) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean isAuthenticated() {
        return loggedInUser != null;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
