package LoginSignUp;

import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, User> users = new HashMap<>();

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }

    public static boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.checkPassword(password);
    }

    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
}
