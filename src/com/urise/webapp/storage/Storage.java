package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(Resume resume);

    void delete(Resume resume);

    Resume[] getAll();

    int size();
}
