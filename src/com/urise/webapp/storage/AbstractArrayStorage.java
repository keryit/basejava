package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void deleteAndMove(int index);

    protected abstract void saveByIndex(Resume resume, int index);

    protected abstract int getIndex(String uuid);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (size == 0) {
            throw new StorageException("ERROR: You tried to delete from the empty storage!", uuid);
        } else if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteAndMove(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException("Warning: The resume already exist in the storage with uuid = " + resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("ERROR: The array storage is full!!!", resume.getUuid());
        } else {
            saveByIndex(resume, index);
            size++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }
}
