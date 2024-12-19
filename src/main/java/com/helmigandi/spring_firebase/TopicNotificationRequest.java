package com.helmigandi.spring_firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicNotificationRequest {
    private String topic;
    private String title;
    private String body;
}
