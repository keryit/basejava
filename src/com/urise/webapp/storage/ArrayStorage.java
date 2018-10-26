package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int count = 0;


    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Warning: The resume already exist in the storage with uuid = " + resume.getUuid());
        } else if (count >= storage.length) {
            System.out.println("ERROR: The array storage is full!!!");
        } else {
            storage[count] = resume;
            count++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("ERROR: You tried to update not existing resume in the storage with uuid = " + resume);
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: You tried to get not existing resume in storage with uuid = " + uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (count == 0) {
            System.out.println("ERROR: You tried to delete from the empty storage!");
        } else if (index < 0) {
            System.out.println("ERROR: You tried to delete not existing resume with uuid = " + uuid);
        } else {
            storage[index] = storage[count - 1];
            storage[count - 1] = null;
            count--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] res = new Resume[count];
        System.arraycopy(storage, 0, res, 0, count);
        return res;
    }

    public int size() {
        return count;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}

