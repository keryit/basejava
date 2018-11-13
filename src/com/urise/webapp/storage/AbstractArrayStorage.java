package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void deleteAndMove(int index);

    protected abstract void saveByIndex(Resume resume, int index);


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void deleteResume(Resume resume, Object index) {
        if (size == 0) {
            throw new StorageException("ERROR: You tried to delete from the empty storage!", resume);
        }
        else {
            deleteAndMove((Integer) index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void saveResume(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("ERROR: The array storage is full!!!", resume);
        } else {
            saveByIndex(resume, (Integer) getIndex(resume));
            size++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume getResume(Resume resume, Object index) {
        return storage[(Integer) index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    @Override
    public void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected boolean isResumeExist(Resume resume) {
        int index = (Integer) getIndex(resume);
        return index >= 0;
    }
}
