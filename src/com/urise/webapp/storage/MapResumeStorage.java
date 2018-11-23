package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new LinkedHashMap<>();


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
    protected Object getIndex(Resume resume) {
        return map.get(resume.getUuid());
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
        return Arrays.asList(map.values().toArray(new Resume[0]));
    }

    @Override
    public int size() {
        return map.size();
    }
}


