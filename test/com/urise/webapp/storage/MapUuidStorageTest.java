package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
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
        Assert.assertSame(newResume, storage.get(newResume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(TEST_RESUME);
    }

    @Test
    public void save() {
        storage.save(TEST_RESUME);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(TEST_RESUME, storage.get(TEST_RESUME.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExit() {
        storage.save(RESUME_2);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(TEST_RESUME.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_2.getUuid());
        Assert.assertEquals(2, storage.size());
        storage.get(RESUME_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete(TEST_RESUME.getUuid());
    }

    @Test(expected = StorageException.class)
    public void deleteFromEmpty() {
        storage.clear();
        storage.delete(TEST_RESUME.getUuid());
    }

    @Test
    public void getAll() {
        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);
        Assert.assertEquals(new ArrayList<>(map.values()), storage.getAll());
        Assert.assertEquals(map.size(), storage.getAll().size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}