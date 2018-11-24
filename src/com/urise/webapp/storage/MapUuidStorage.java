package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

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
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        map.put((String) index, resume);
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
    public List<Resume> getAll(){
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
