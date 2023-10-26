package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.cms.CmsUser;
import com.untucapital.usuite.utg.model.po.PoUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "contact_detail_id"})
})

public class User extends AbstractEntity {

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String password;

    @Column(name = "reset_token")
    private String resetPasswordToken;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @NotNull
    @JoinColumn(name = "contact_detail_id", nullable = false)
    private ContactDetail contactDetail;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "cms_user_id")
    private CmsUser cmsUser;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "po_user_id")
    private PoUser poUser;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotNull
    @Column(nullable = false)
    private boolean verified;

    private String branch;

    private String creditCommitGroup;

    private String dirtOfBirth;

    private String maritalStatus;

    private String Gender;

    private String City;

    private String suburb;

    private String streetName;

    private String streetNumber;

    private String musoniClientId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public String getCreditCommitGroup() {
        return creditCommitGroup;
    }

    public void setCreditCommitGroup(String creditCommitGroup) {
        this.creditCommitGroup = creditCommitGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDirtOfBirth() {
        return dirtOfBirth;
    }

    public void setDirtOfBirth(String dirtOfBirth) {
        this.dirtOfBirth = dirtOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getMusoniClientId() {
        return musoniClientId;
    }

    public void setMusoniClientId(String musoniClientId) {
        this.musoniClientId = musoniClientId;
    }

    public CmsUser getCmsUser() {
        return cmsUser;
    }

    public void setCmsUser(CmsUser cmsUser) {
        this.cmsUser = cmsUser;
    }

    public PoUser getPoUser() {
        return poUser;
    }

    public void setPoUser(PoUser poUser) {
        this.poUser = poUser;
    }
}
