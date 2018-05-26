package com.valencia.oscar.w2d5_ex1;

public class ContactItem {
    private String name;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String photo;

    public ContactItem(String name, String lastName, int age, String email, String phone, String photo) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }
}
