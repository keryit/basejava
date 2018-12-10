package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private Organization organization;
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

    public OrganizationSection(Organization organization,  LocalDate startYear, LocalDate endYear, String title, String description) {
        this.organization = organization;
        this.startYear = startYear;
        this.endYear = endYear;
        this.title = title;
        this.description = description;
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
        return startYear.equals(that.startYear) &&
                endYear.equals(that.endYear) &&
                title.equals(that.title) &&
                Objects.equals(description, that.description) &&
                organization.equals(that.organization) &&
                Objects.equals(listOrganizations, that.listOrganizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startYear, endYear, title, description, organization, listOrganizations);
    }


}
