package com.example.learn09;

public class Contact {
    private String name;
    private String email;
    private String imageUrl;

    public Contact() {
        // Constructor mặc định cần thiết để Firebase có thể deserialize dữ liệu
    }

    public Contact(String name, String email, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    // Getter và Setter cho các thuộc tính
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

