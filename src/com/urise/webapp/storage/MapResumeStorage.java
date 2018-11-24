package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getResume(Object index) {
        return (Resume) index;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object index) {
        map.remove(((Resume) index).getUuid());
    }

    @Override
    protected Object getIndex(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Resume newResume, Object index) {
        map.put(newResume.getUuid(), newResume);
    }

    @Override
    protected boolean isResumeExist(Object index) {
        return map.containsValue((Resume) index);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}


