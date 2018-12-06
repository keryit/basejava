package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {

        //adding contacts to resume(phone, skype, email)
        Resume resume = new Resume("uuid1", "Resume1");
        Map<Contacts, String> mapContact = new HashMap<>();
        mapContact.put(Contacts.PHONE, "+380990987666");
        mapContact.put(Contacts.SKYPE, "skype");
        mapContact.put(Contacts.EMAIL, "test@gmail.com");
        mapContact.put(Contacts.SOCIAL_PROFILE, "github_profile");
        mapContact.put(Contacts.HOME_PAGE, "url_to_gome_page");
        resume.setContactsMap(mapContact);
        System.out.println(resume.getContactsMap());

        //adding position
        Map<SectionType, AbstractSection> mapSection = new HashMap<>();
        mapSection.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по " +
                "Java Web и Enterprise технологиям"));

        //adding personal info
        mapSection.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));

        //creating list of responsibility
        List<String> listResponsipility = new ArrayList<>();
        listResponsipility.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listResponsipility.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listResponsipility.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        // adding achievement to resume
        mapSection.put(SectionType.ACHIEVEMENT, new MultipleTextSection(listResponsipility));
        resume.setSectionMap(mapSection);

        //creating list of Qualifications and adding to resume
        List<String> listQ = new ArrayList<>();
        listQ.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQ.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQ.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        mapSection.put(SectionType.QUALIFICATIONS, new MultipleTextSection(listQ));

        //adding experience- Organization, start year, end year, position, experience
        OrganizationSection org = new OrganizationSection("Java Online Projects", LocalDate.of(2013, 10, 01), LocalDate.now(),
                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        OrganizationSection org2 = new OrganizationSection("Wrike", LocalDate.of(2014, 10, 01),
                LocalDate.of(2016, 01, 01), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        List<OrganizationSection> listOrganiz = new ArrayList<>();
        listOrganiz.add(org);
        listOrganiz.add(org2);
        mapSection.put(SectionType.EXPERIENCE, new OrganizationSection(listOrganiz));

        //adding education
        OrganizationSection edu1 = new OrganizationSection("Coursera", LocalDate.of(2013, 03, 01),
                LocalDate.of(2013, 05, 01), "Functional Programming Principles in Scala\" by Martin Odersky", null);
        OrganizationSection edu2 = new OrganizationSection("Luxoft", LocalDate.of(2011, 03, 01),
                LocalDate.of(2011, 04, 01), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        List<OrganizationSection> listEducations = new ArrayList<>();
        listEducations.add(edu1);
        listEducations.add(edu2);
        mapSection.put(SectionType.EDUCATION, new OrganizationSection(listEducations));
        System.out.println(resume.getSectionMap());

    }
}
