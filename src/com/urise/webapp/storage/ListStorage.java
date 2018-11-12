package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume r) {
        if (!list.contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        }
        list.set(getIndex(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        if (list.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        list.add(r);
    }

    @Override
    public Resume get(String uuid) {
        Resume r = new Resume(uuid);
        if (!list.contains(r)) {
            throw new NotExistStorageException(uuid);
        }
        return list.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);
        if (!list.contains(r)) {
            throw new NotExistStorageException(uuid);
        }
        list.remove(r);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }

    private int getIndex(String uuid) {
        Resume r = new Resume(uuid);
        return list.indexOf(r);
    }
}
