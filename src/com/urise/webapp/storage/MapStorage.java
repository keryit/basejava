package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;


public class MapStorage extends AbstractStorage {

    private HashMap<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void update(Resume r) {
        if (!map.containsValue(r)) {
            throw new NotExistStorageException(r.getUuid());
        }
        map.put(r.getUuid(), r);
    }

    @Override
    public void save(Resume r) {
        if (map.containsValue(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        map.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        if (!map.containsValue(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return map.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);
        if (map.size() == 0) {
            throw new StorageException("ERROR: You tried to delete from the empty storage!", uuid);
        }
        if (!map.containsValue(r)) {
            throw new NotExistStorageException(uuid);
        }
        map.remove(uuid, r);
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
