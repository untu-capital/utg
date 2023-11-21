package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchaseOrderTransactions")
public class PurchaseOrderTransactions extends AbstractEntity{

    @Column(name = "po_item", nullable = false)
    @NotNull
    private String poItem;

    @Column(name = "po_supplier", nullable = false)
    @NotNull
    private String poSupplier;

    @Column(name = "po_category", nullable = false)
    @NotNull
    private String poCategory;

    @Column(name = "po_quantity", nullable = false)
    @NotNull
    private String poQuantity;

    @Column(name = "po_amount", nullable = false)
    @NotNull
    private String poAmount;

    @Column(name = "po_requisition_id", nullable = false)
    @NotNull
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
