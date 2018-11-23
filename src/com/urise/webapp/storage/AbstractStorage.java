package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(Object index);

    protected abstract void saveResume(Resume resume, Object index);

    protected abstract void deleteResume(Object index);

    protected abstract Object getIndex(Resume resume);

    protected abstract void updateResume(Resume newResume, Object index);

    protected abstract boolean isResumeExist(Object index);

    @Override
    public void delete(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            deleteResume(index);
        }
    }

    @Override
    public void save(Resume resume) {
        Object index = getIndex(resume);
        if (isResumeExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, index);
        }
    }

    @Override
    public Resume get(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        return getResume(index);
    }

    @Override
    public void update(Resume resume) {
        Object index = getIndex(resume);
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, index);
        }
    }
}
