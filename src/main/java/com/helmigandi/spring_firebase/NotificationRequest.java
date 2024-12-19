package com.helmigandi.spring_firebase;

public class NotificationRequest {
    private String token;
    private String title;
    private String body;

    public NotificationRequest() {
    }

    public NotificationRequest(String title, String token, String body) {
        this.title = title;
        this.token = token;
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
