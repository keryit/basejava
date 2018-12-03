package com.urise.webapp.model;

import java.util.Objects;

public class TextAndYearSection extends Section {

    private String nameSectionInTable;
    private String startYear;
    private String endYear;
    private String positionOrCourse;
    private String description;

    public TextAndYearSection(String nameSectionInTable, String startYear, String endYear, String positionOrCourse, String description) {
        this.nameSectionInTable = nameSectionInTable;
        this.startYear = startYear;
        this.endYear = endYear;
        this.positionOrCourse = positionOrCourse;
        this.description = description;
    }

    public String getNameSectionInTable() {
        return nameSectionInTable;
    }

    public void setNameSectionInTable(String nameSectionInTable) {
        this.nameSectionInTable = nameSectionInTable;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getPositionOrCourse() {
        return positionOrCourse;
    }

    public void setPositionOrCourse(String positionOrCourse) {
        this.positionOrCourse = positionOrCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextAndYearSection that = (TextAndYearSection) o;
        return Objects.equals(nameSectionInTable, that.nameSectionInTable) &&
                Objects.equals(startYear, that.startYear) &&
                Objects.equals(endYear, that.endYear) &&
                Objects.equals(positionOrCourse, that.positionOrCourse) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSectionInTable, startYear, endYear, positionOrCourse, description);
    }
}
