package com.urise.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<Contacts, String> contactsMap;
    private Map<SectionType, Section> sectionMap;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContactsMap(Map<Contacts, String> contactsMap) {
        this.contactsMap = contactsMap;
    }

    public void setSectionMap(Map<SectionType, Section> sectionMap) {
        this.sectionMap = sectionMap;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<Contacts, String> getContactsMap() {
        return contactsMap;
    }

    public Map<SectionType, Section> getSectionMap() {
        return sectionMap;
    }

    @Override
    public String toString() {
        return uuid + " with name - " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        if (!uuid.equals(resume.getUuid())) return false;
        return fullName.equals(resume.getFullName());
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }
}
