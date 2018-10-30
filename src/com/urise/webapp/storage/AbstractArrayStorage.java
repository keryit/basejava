package com.urise.webapp.storage;

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
            System.out.println("ERROR: You tried to delete from the empty storage!");
        } else if (index < 0) {
            System.out.println("ERROR: You tried to delete not existing resume with uuid = " + uuid);
        } else {
            deleteAndMove(index);
            size--;
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Warning: The resume already exist in the storage with uuid = " + resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR: The array storage is full!!!");
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
            System.out.println("Resume " + uuid + " not exist");
            return null;
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
            System.out.println("ERROR: You tried to update not existing resume in the storage with uuid = " + resume);
        } else {
            storage[index] = resume;
        }
    }
}
