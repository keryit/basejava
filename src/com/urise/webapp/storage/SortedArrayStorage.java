package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteByIndex(int index) {
        int numElements = size - index - 1;
        if (numElements > 0) {
            System.arraycopy(storage, index + 1, storage, index, numElements);
        }
    }

    @Override
    protected void saveByIndex(Resume resume, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = resume;
    }

    @Override
    protected Integer getIndex(String uuid) {
        Resume resume = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
