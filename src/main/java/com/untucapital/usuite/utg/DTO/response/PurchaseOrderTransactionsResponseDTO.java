package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


public class PurchaseOrderTransactionsResponseDTO extends AbstractEntityDTO {


    private String poItem;

    private String poSupplier;

    private String poCategory;

    private String poQuantity;

    private String poAmount;

    private String poRequisitionId;


    public String getPoItem() {
        return poItem;
    }

    public void setPoItem(String poItem) {
        this.poItem = poItem;
    }

    public String getPoSupplier() {
        return poSupplier;
    }

    public void setPoSupplier(String poSupplier) {
        this.poSupplier = poSupplier;
    }

    public String getPoCategory() {
        return poCategory;
    }

    public void setPoCategory(String poCategory) {
        this.poCategory = poCategory;
    }

    public String getPoQuantity() {
        return poQuantity;
    }

    public void setPoQuantity(String poQuality) {
        this.poQuantity = poQuality;
    }

    public String getPoAmount() {
        return poAmount;
    }

    public void setPoAmount(String poAmount) {
        this.poAmount = poAmount;
    }

    public String getPoRequisitionId() {
        return poRequisitionId;
    }

    public void setPoRequisitionId(String poRequisitionId) {
        this.poRequisitionId = poRequisitionId;
    }
}
