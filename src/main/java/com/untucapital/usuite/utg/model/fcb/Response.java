package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_response")
public class Response extends AbstractEntity {

    private Integer code;

    private String individual;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    @JsonProperty(value = "Report")
    private List<Report> report;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Address> addresses;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Search> searches;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Directorship> directorships;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Active> active;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Inactive> inactive;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Exposure> exposures;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    @JsonProperty(value = "additional_info")
    private List<AdditionalInfo> additionalInfo;

    public List<Report> getReport() {
        return report;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual;
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

    public List<Exposure> getExposures() {
        return exposures;
    }

    public void setExposures(List<Exposure> exposures) {
        this.exposures = exposures;
    }

    public List<AdditionalInfo> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
