package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;

public class AbstractStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    protected static final String UUID_2 = "uuid2";
    protected static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    protected static final String UUID_4 = "uuid4_new";
    protected static final Resume TEST_RESUME = new Resume(UUID_4, "Name4");

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }
}