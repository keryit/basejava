package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getResume(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void saveResume(Resume resume, String searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected boolean isResumeExist(String searchKey) {
        return map.containsKey(searchKey);
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
