package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteAndMove(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveByIndex(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected Object getIndex(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.equals(storage[i]))
                return i;
        }
        return -1;
    }
}

