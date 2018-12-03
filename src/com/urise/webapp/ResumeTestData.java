package com.urise.webapp;

import com.urise.webapp.model.Contacts;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Resume1");
        Map<Contacts, String> mapContact = new HashMap<>();
        mapContact.put(Contacts.PHONE, "+380990987666");
        mapContact.put(Contacts.EMAIL, "test@gmail.com");
        resume.setContactsMap(mapContact);
        System.out.println(resume.getContactsMap());
    }
}
