package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public abstract class AbstractStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    protected static final String UUID_2 = "uuid2";
    protected static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    protected static final String UUID_4 = "uuid4_new";
    protected static final Resume TEST_RESUME = new Resume(UUID_4, "Name4");

    static {
        Map<Contacts, String> mapContact = new HashMap<>();
        mapContact.put(Contacts.PHONE, "+380990987666");
        mapContact.put(Contacts.SKYPE, "skype");
        mapContact.put(Contacts.EMAIL, "test@gmail.com");
        mapContact.put(Contacts.SOCIAL_PROFILE, "github_profile");
        mapContact.put(Contacts.HOME_PAGE, "url_to_gome_page");
        RESUME_1.setContactsMap(mapContact);
        System.out.println(RESUME_1.getContactsMap());

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
        RESUME_1.setSectionMap(mapSection);

        //creating list of Qualifications and adding to resume
        List<String> listQ = new ArrayList<>();
        listQ.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQ.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQ.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        mapSection.put(SectionType.QUALIFICATIONS, new MultipleTextSection(listQ));

        //adding experience- Organization, start year, end year, position, experience
        Organization company = new Organization("Java Online Projects");
        OrganizationSection org = new OrganizationSection(company, LocalDate.of(2013, 10, 01), LocalDate.now(),
                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        OrganizationSection org2 = new OrganizationSection(new Organization("Wrike"), LocalDate.of(2014, 10, 01),
                LocalDate.of(2016, 01, 01), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        List<OrganizationSection> listOrganiz = new ArrayList<>();
        listOrganiz.add(org);
        listOrganiz.add(org2);
        mapSection.put(SectionType.EXPERIENCE, new OrganizationSection(listOrganiz));

        //adding education
        OrganizationSection edu1 = new OrganizationSection(new Organization("Coursera"), LocalDate.of(2013, 03, 01),
                LocalDate.of(2013, 05, 01), "Functional Programming Principles in Scala\" by Martin Odersky", null);
        OrganizationSection edu2 = new OrganizationSection(new Organization("Luxoft"), LocalDate.of(2011, 03, 01),
                LocalDate.of(2011, 04, 01), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        List<OrganizationSection> listEducations = new ArrayList<>();
        listEducations.add(edu1);
        listEducations.add(edu2);
        mapSection.put(SectionType.EDUCATION, new OrganizationSection(listEducations));
    }

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Name1");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(TEST_RESUME);
    }

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExit() {
        storage.save(RESUME_2);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete(UUID_4);
    }

    @Test(expected = StorageException.class)
    public void deleteFromEmpty() {
        storage.clear();
        storage.delete(UUID_4);
    }

    @Test
    public void getAll() {
        List<Resume> list = new ArrayList<Resume>();
        list.add(RESUME_1);
        list.add(RESUME_2);
        list.add(RESUME_3);
        Assert.assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}