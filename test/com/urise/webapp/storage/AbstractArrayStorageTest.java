package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4_new";
    private static final Resume TEST_RESUME = new Resume(UUID_4);
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
        storage.delete(RESUME_2);
        Assert.assertEquals(2, storage.size());
        storage.get(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete(TEST_RESUME);
    }

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(TEST_RESUME));
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
        storage.save(new Resume());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(TEST_RESUME);
    }

    @Test
    public void getAll() {
        Resume[] res = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(res, storage.getAll());
        Assert.assertEquals(res.length, storage.getAll().length);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(newResume));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(TEST_RESUME);
    }
}