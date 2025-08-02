package com.example.planner;

import AI.ChatService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class ChatController {

    @FXML private TextField userInput;
    @FXML private VBox chatBox;
    @FXML private ScrollPane scrollPane;

    // Label to show "AI is typing..." indicator
    private Label typingIndicatorLabel;

    private final ChatService chatService = new ChatService();

    private Label currentAIResponseLabel = null;

    @FXML
    public void initialize() {
        // Setup Enter key to send message
        userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSend();
                event.consume(); // prevent newline in TextField
            }
        });

        // Initialize typing indicator label but don't add yet
        typingIndicatorLabel = new Label("AI is typing...");
        typingIndicatorLabel.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        typingIndicatorLabel.setPadding(new Insets(5, 10, 5, 10));
    }

    @FXML
    private void handleSend() {
        String message = userInput.getText().trim();
        if (message.isEmpty()) return;

        addUserMessage(message);
        userInput.clear();

        currentAIResponseLabel = createMessageLabel("", "#3399ff", "-fx-text-fill: white;");
        HBox bubble = new HBox(currentAIResponseLabel);
        bubble.setAlignment(Pos.CENTER_LEFT);

        Platform.runLater(() -> {
            chatBox.getChildren().add(bubble);
            addTypingIndicator();
            scrollToBottom();
        });

        chatService.sendMessage(
                message,

                chunk -> Platform.runLater(() -> {
                    // Remove typing indicator on first chunk
                    if (chatBox.getChildren().contains(typingIndicatorLabel)) {
                        chatBox.getChildren().remove(typingIndicatorLabel);
                    }
                    // Append chunk text
                    String updatedText = currentAIResponseLabel.getText() + chunk;
                    currentAIResponseLabel.setText(updatedText);
                    scrollToBottom();
                }),

                () -> Platform.runLater(() -> {
                    // Remove typing indicator if still present
                    removeTypingIndicator();
                    currentAIResponseLabel = null;
                }),

                error -> Platform.runLater(() -> {
                    removeTypingIndicator();
                    if (currentAIResponseLabel != null) {
                        currentAIResponseLabel.setText("⚠️ " + error);
                        currentAIResponseLabel = null;
                        scrollToBottom();
                    }
                })
        );
    }

    private void addTypingIndicator() {
        if (!chatBox.getChildren().contains(typingIndicatorLabel)) {
            chatBox.getChildren().add(typingIndicatorLabel);
            scrollToBottom();
        }
    }

    private void removeTypingIndicator() {
        chatBox.getChildren().remove(typingIndicatorLabel);
    }

    private void addUserMessage(String message) {
        Label msgLabel = createMessageLabel(message, "#ff8800", "-fx-text-fill: white;");
        HBox bubble = new HBox(msgLabel);
        bubble.setAlignment(Pos.CENTER_RIGHT);
        chatBox.getChildren().add(bubble);
        scrollToBottom();
    }

    private Label createMessageLabel(String text, String bgColor, String textStyle) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setPadding(new Insets(10));
        label.setMaxWidth(400);
        label.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                        "-fx-background-radius: 12;" +
                        textStyle
        );
        return label;
    }

    private void scrollToBottom() {
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
    @FXML
    private Button backButton;
    @FXML
    private void handleBackButton() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/example/planner/DashBoard.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) backButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("TO Dash Board");

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load Task Creation screen.");
            alert.show();
        }
    }

    @FXML private Button stopButton;

    @FXML
    private void handleStop() {
        chatService.stopCurrentResponse();
        removeTypingIndicator();

        if (currentAIResponseLabel != null) {
            currentAIResponseLabel.setText(currentAIResponseLabel.getText() + "\n\n[✋ Stopped by user]");
            currentAIResponseLabel = null;
        }
    }

}
