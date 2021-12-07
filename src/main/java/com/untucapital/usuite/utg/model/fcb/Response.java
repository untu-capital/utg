package com.untucapital.usuite.utg.model.fcb;

import java.util.List;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Response {

    private List<Report> report;

    private List<Address> addresses;

    private List<Search> searches;

    private List<Directorship> directorships;

    private List<Active> active;

    private List<Inactive> inactive;

    private List<Object> exposures;

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }

    public List<Directorship> getDirectorships() {
        return directorships;
    }

    public void setDirectorships(List<Directorship> directorships) {
        this.directorships = directorships;
    }

    public List<Active> getActive() {
        return active;
    }

    public void setActive(List<Active> active) {
        this.active = active;
    }

    public List<Inactive> getInactive() {
        return inactive;
    }

    public void setInactive(List<Inactive> inactive) {
        this.inactive = inactive;
    }

    public List<Object> getExposures() {
        return exposures;
    }

    public void setExposures(List<Object> exposures) {
        this.exposures = exposures;
    }
}
