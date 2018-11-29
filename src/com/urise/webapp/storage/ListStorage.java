package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Resume getResume(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        int del = searchKey;
        list.remove(del);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected boolean isResumeExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
