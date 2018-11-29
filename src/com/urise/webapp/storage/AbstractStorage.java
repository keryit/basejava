package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private final static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract Resume getResume(SK searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(Resume newResume, SK searchKey);

    protected abstract boolean isResumeExist(SK searchKey);

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK index = getSearchKey(uuid);
        if (!isResumeExist(index)) {
            LOG.warning("Resume with uuid = " + uuid + " does not exist!");
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK index = getSearchKey(resume.getUuid());
        if (isResumeExist(index)) {
            LOG.warning("Resume with uuid = " + resume.getUuid() + " already exist!");
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK index = getSearchKey(uuid);
        if (!isResumeExist(index)) {
            LOG.warning("Resume with uuid = " + uuid + " does not exist!");
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK index = getSearchKey(resume.getUuid());
        if (!isResumeExist(index)) {
            LOG.warning("Resume - " + resume + " does not exist!");
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, index);
        }
    }
}
