package org.hse.software.construction.HW2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hse.software.construction.HW2.view.ConsoleView;
import org.hse.software.construction.HW2.view.View;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @JsonSerialize
    public ArrayList<User> users = new ArrayList<>();

    @Builder.Default
    private static View view = new ConsoleView();

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

    public void login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(hashPassword(password))) {
                System.out.println("Logged in as " + user.getUsername());
                return;
            }
        }
        view.showErrorMessage("Invalid username or password");
    }

    public void logout() {
        System.out.println("Logged out");
    }

    public void signUp(String username, String password, UserRole role) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            view.showErrorMessage("Username already exists");
        } else if (username.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Username and password cannot be empty");
        }

        User user = User.builder()
                .username(username)
                .password(hashPassword(password))
                .role(role)
                .build();
        addUser(user);
        System.out.println("Signed up as " + user.getUsername());
    }
}
