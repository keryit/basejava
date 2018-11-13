package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;


public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(Resume resume, Object index) {
        return map.get(resume.getUuid());
    }

    @Override
    protected void saveResume(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Resume resume, Object index) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Object getIndex(Resume resume) {
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().equals(resume.getUuid())).findAny();
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isResumeExist(Resume resume) {
        return map.containsValue(resume);
    }

    @Override
    public void clear() {
        map.clear();
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
