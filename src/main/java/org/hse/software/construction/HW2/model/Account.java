package org.hse.software.construction.HW2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @JsonSerialize
    public ArrayList<User> users = new ArrayList<>();

    private static ConsoleView consoleView = new ConsoleView();

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(hashPassword(password))) {
                consoleView.showRegistrationSuccess(username);
                return true;
            }
        }
        consoleView.showErrorMessage("Invalid username or password");
        return false;
    }

    public boolean signUp(String username, String password, UserRole role) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            consoleView.showErrorMessage("Username already exists");
            return false;
        } else if (username.isEmpty() || password.isEmpty()) {
            consoleView.showErrorMessage("Username and password cannot be empty");
            return false;
        }

        User user = User.builder()
                .username(username)
                .password(hashPassword(password))
                .role(role)
                .build();
        addUser(user);
        consoleView.showRegistrationSuccess(username);
        return true;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
