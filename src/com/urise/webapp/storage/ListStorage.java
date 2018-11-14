package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Resume getResume(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        list.add(resume);
    }

    @Override
    protected void deleteResume(Object index) {
        int del = (Integer) index;
        list.remove(del);
    }

    @Override
    protected Object getIndex(Resume resume) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(resume))
                return i;
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    protected boolean isResumeExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
