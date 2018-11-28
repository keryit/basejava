package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume newResume, Object searchKey);

    protected abstract boolean isResumeExist(Object searchKey);

    @Override
    public void delete(String uuid) {
        Object index = getSearchKey(uuid);
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    @Override
    public void save(Resume resume) {
        Object index = getSearchKey(resume.getUuid());
        if (isResumeExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object index = getSearchKey(uuid);
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    @Override
    public void update(Resume resume) {
        Object index = getSearchKey(resume.getUuid());
        if (!isResumeExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, index);
        }
    }
}
