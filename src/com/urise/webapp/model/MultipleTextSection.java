package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class MultipleTextSection extends AbstractSection {

    private List<String> list;

    public MultipleTextSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleTextSection that = (MultipleTextSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
