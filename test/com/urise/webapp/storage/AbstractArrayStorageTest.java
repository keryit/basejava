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
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4_new";
    private Storage storage;
    private Resume TEST_RESUME = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
        storage.delete(TEST_RESUME.getUuid());
    }

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExit() {
        storage.save(new Resume(UUID_2));
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
        Resume resume = new Resume(UUID_2);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] res = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(res, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(TEST_RESUME);
    }
}