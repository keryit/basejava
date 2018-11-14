package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(Object index) {
        return map.get((String) index);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object index) {
        map.remove((String) index);
    }

    @Override
    protected Object getIndex(Resume resume) {
        return resume.getUuid();
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isResumeExist(Object index) {
        return map.containsKey((String) index);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
