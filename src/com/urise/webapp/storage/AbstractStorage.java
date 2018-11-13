package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(Resume resume, Object index);

    protected abstract void saveResume(Resume resume);

    protected abstract void deleteResume(Resume resume, Object index);

    protected abstract Object getIndex(Resume resume);

    protected abstract void updateResume(Resume resume, Object index);

    protected abstract boolean isResumeExist(Resume resume);

    @Override
    public void delete(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume);
        } else {
            deleteResume(resume, index);
        }
    }

    @Override
    public void save(Resume resume) {
        if (isResumeExist(resume)) {
            throw new ExistStorageException(resume);
        } else {
            saveResume(resume);
        }
    }

    @Override
    public Resume get(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume);
        }
        return getResume(resume, index);
    }

    @Override
    public void update(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume);
        } else {
            updateResume(resume, index);
        }
    }
}
