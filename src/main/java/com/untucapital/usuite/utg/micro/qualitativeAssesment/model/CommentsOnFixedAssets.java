package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CommentsOnFixedAssetsMicro")
public class CommentsOnFixedAssets extends AbstractEntity {
    private String loanId;
    private String description;

    public CommentsOnFixedAssets() {
    }

    public CommentsOnFixedAssets(String loanId, String description) {
        this.loanId = loanId;
        this.description = description;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommentsOnFixedAssetsService{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
