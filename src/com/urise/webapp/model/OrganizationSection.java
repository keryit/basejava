package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private String nameCompany;
    private LocalDate startYear;
    private LocalDate endYear;
    private String title;
    private String description;

    public OrganizationSection(List<OrganizationSection> listOrganizations) {
        this.listOrganizations = listOrganizations;
    }

    private List<OrganizationSection> listOrganizations;

    public List<OrganizationSection> getListOrganizations() {
        return listOrganizations;
    }

    public void setListOrganizations(List<OrganizationSection> listOrganizations) {
        this.listOrganizations = listOrganizations;
    }

    public OrganizationSection(String nameCompany, LocalDate startYear, LocalDate endYear, String title, String description) {
        this.nameCompany = nameCompany;
        this.startYear = startYear;
        this.endYear = endYear;
        this.title = title;
        this.description = description;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public LocalDate getStartYear() {
        return startYear;
    }

    public void setStartYear(LocalDate startYear) {
        this.startYear = startYear;
    }

    public LocalDate getEndYear() {
        return endYear;
    }

    public void setEndYear(LocalDate endYear) {
        this.endYear = endYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(nameCompany, that.nameCompany) &&
                Objects.equals(startYear, that.startYear) &&
                Objects.equals(endYear, that.endYear) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCompany, startYear, endYear, title, description);
    }
}
