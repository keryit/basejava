package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4_new";
    private static final Resume TEST_RESUME = new Resume(UUID_4, "Name4");
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
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

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_2);
    }

    @Test(expected = StorageException.class)
    public void saveToFullStorage() {
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - 3; i++) {
                storage.save(new Resume("resume" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Something was wrong during adding to storage!");
        }
        storage.save(new Resume("NewResumeName"));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAll() {
        List<Resume> list = new ArrayList<Resume>();
        list.add(RESUME_1);
        list.add(RESUME_2);
        list.add(RESUME_3);
        Assert.assertEquals(list, storage.getAll());
        Assert.assertEquals(list.size(), storage.getAll().size());
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
}