package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file);

    @Override
    protected Resume getResume(File file) {
        return doRead(file);
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        file.delete();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(Resume newResume, File file) {
        try {
            doWrite(newResume, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected boolean isResumeExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                file.delete();
            }
        }
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        list.add(doRead(directory));
        return list;
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
