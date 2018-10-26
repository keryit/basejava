package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count;

    public void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = 0;
    }

    public void save(Resume resume) {

        if (!isResumePresent(resume.getUuid()) && count < storage.length) {
            storage[count] = resume;
            count++;
        } else if (isResumePresent(resume.getUuid()) && count < storage.length) {
            System.out.println("Warning: The resume already exist in the storage with uuid = " + resume.getUuid());

        } else if (count > storage.length) {
            System.out.println("ERROR: The array storage is full!!!");
        }
    }

    public void update(Resume resume) {
        if (!isResumePresent(resume.getUuid())) {
            System.out.println("ERROR: You tried to update not existing resume in the storage with uuid = " + resume);
        } else {
            storage[getIdResumeIfPresent(resume.getUuid())] = resume;
        }
    }

    public Resume get(String uuid) {

        if (!isResumePresent(uuid)) {
            System.out.println("ERROR: You tried to get not existing resume in storage with uuid = " + uuid);
            return null;
        }
        return storage[getIdResumeIfPresent(uuid)];
    }

    public void delete(String uuid) {

        if (!isResumePresent(uuid)) {
            System.out.println("ERROR: You tried to delete not existing resume with uuid = " + uuid);
        } else {
            storage[getIdResumeIfPresent(uuid)] = storage[count - 1];
            storage[count - 1] = null;
            count--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] res = new Resume[count];
        for (int i = 0; i < count; i++) {
            res[i] = storage[i];
        }
        return res;
    }

    public int size() {

        return count;
    }


    private boolean isResumePresent(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }


    private int getIdResumeIfPresent(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        System.out.println("No such resume in the storage");
        return -1;
    }

}

