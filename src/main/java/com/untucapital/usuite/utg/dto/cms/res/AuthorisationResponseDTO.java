package com.untucapital.usuite.utg.dto.cms.res;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

public class AuthorisationResponseDTO extends AbstractEntityDTO {

    private String branchId;
    private String authLevel;
    private String userId;


    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
