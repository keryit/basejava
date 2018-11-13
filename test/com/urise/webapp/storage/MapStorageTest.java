package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
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

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(TEST_RESUME));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExit() {
        storage.save(RESUME_2);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(TEST_RESUME);
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

    @Test(expected = StorageException.class)
    public void deleteFromEmpty() {
        storage.clear();
        storage.delete(TEST_RESUME);
    }

    @Test
    public void getAll() {
        Map<String, Resume> map = new LinkedHashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);
        Assert.assertArrayEquals(map.values().toArray(), storage.getAll());
        Assert.assertEquals(map.size(), storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}