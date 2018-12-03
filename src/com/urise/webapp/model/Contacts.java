package com.urise.webapp.model;

public enum Contacts {

    PHONE("Phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    SOCIAL_PROFILE("Profile"),
    HOME_PAGE("Home page");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
