package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class ExistStorageException extends StorageException {

    public ExistStorageException(Resume resume) {
        super("Resume " + resume.getUuid() + " already exist!", resume);
    }
}
