package com.helmigandi.spring_firebase;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseMessagingService {

    public String sendNotification(NotificationRequest request) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        .build())
                .setToken(request.getToken())
//                    .putAllData()
//                    .putData()
                // Android config
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(AndroidNotification.builder()
                                .setClickAction("NOTIF_CLICK_RESI")
                                .setSound("default")
                                .build())
                        .build())
                // iOS config
                .setApnsConfig(ApnsConfig.builder()
                        .putHeader("apns-priority", "5")
                        .putHeader("apns-push-type", "alert")
                        .setAps(Aps.builder()
                                .setCategory("NOTIF_CLICK_RESI")
                                .setSound("default")
                                .build())
                        .build())
                .build();

        return FirebaseMessaging.getInstance().send(message);
    }

    // Send to multiple tokens
    public String sendNotificationToMultipleTokens(List<String> tokens, String title, String body) {
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .addAllTokens(tokens)
                .build();
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
            return response.toString();
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // Send to a specific topic
    public String sendToTopic(String topic, String title, String body) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setTopic(topic)  // This specifies the topic
                .putData("click_action", "NOTIF_CLICK_RESI")
                .putData("screen", "NOTIF_CLICK_RESI")  // Used to determine which screen to open
                // Android config
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(AndroidNotification.builder()
                                .setClickAction("NOTIF_CLICK_RESI")
                                .setDefaultVibrateTimings(true)
//                                .setChannelId("default")
                                .setDefaultSound(true)
                                .build())
                        .build())
                // iOS config
                .setApnsConfig(ApnsConfig.builder()
                        // 5 is normal & 10 is high
                        .putHeader("apns-priority", "5")
                        .putHeader("apns-push-type", "alert")
                        .setAps(Aps.builder()
                                .setCategory("NOTIF_CLICK_RESI")
                                .setSound("default")
                                .setContentAvailable(true)  // Important for background processing
                                .build())
                        .build())
                .build();

        return FirebaseMessaging.getInstance().send(message);
    }
}
