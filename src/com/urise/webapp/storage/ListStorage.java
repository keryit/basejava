package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Resume getResume(Resume resume, Object index) {
        return list.stream().filter
                (list -> resume.getUuid().equals(list.getUuid())).
                findAny().
                orElse(null);
    }

    @Override
    protected void saveResume(Resume resume) {
        list.add(resume);
    }

    @Override
    protected void deleteResume(Resume resume, Object index) {
        list.remove(resume);
    }

    @Override
    protected Object getIndex(Resume resume) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(resume.getUuid()))
                return i;
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    protected boolean isResumeExist(Resume resume) {
        return (Integer) getIndex(resume) >= 0;
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
