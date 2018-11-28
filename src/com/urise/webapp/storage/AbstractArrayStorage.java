package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void deleteByIndex(int index);

    protected abstract void saveByIndex(Resume resume, int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void deleteResume(Object searchKey) {
        if (size == 0) {
            throw new StorageException("ERROR: You tried to delete from the empty storage!", storage[(Integer) searchKey].getUuid());
        } else {
            deleteByIndex((Integer) searchKey);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void saveResume(Resume resume, Object searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("ERROR: The array storage is full!!!", resume.getUuid());
        } else {
            saveByIndex(resume, (Integer) searchKey);
            size++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> list = Arrays.asList(storage);
        return list.subList(0, size);
    }

    @Override
    public void updateResume(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected boolean isResumeExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
}
