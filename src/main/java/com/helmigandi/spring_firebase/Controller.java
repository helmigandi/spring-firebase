package com.helmigandi.spring_firebase;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class Controller {
    private final FirebaseMessagingService firebaseMessagingService;

    public Controller(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            String response = firebaseMessagingService.sendNotification(request);
            return ResponseEntity.ok(response);
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send notification");
        }
    }

    @PostMapping("/topic")
    public ResponseEntity<String> sendToTopic(@RequestBody TopicNotificationRequest request) {
        try {
            String response = firebaseMessagingService.sendToTopic(
                    request.getTopic(),
                    request.getTitle(),
                    request.getBody()
            );
            return ResponseEntity.ok("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
        }
    }
}
