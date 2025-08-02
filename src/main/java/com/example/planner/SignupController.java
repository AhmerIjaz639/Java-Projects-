package com.example.planner;

import LoginSignUp.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    @FXML
    private void handleSignup() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "Please fill all fields.");
        } else if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
        } else if (UserDatabase.userExists(username)) {
            showAlert("Error", "Username already exists.");
        } else {
            boolean success = UserDatabase.register(username, password);
            if (success) {
                showAlert("Success", "User registered successfully.");
                loadLogin();
            } else {
                showAlert("Error", "Registration failed.");
            }
        }
    }

    @FXML
    private void loadLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/planner/login.fxml"));

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            showAlert("Error", "Failed to load login screen.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
}
