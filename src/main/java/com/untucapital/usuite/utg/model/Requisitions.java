package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requisitions")
public class Requisitions extends AbstractEntity {

    @NotNull
    @Column(nullable = false, name = "po_number")
    private String poNumber;

    @NotNull
    @Column(nullable = false, name = "po_name")
    private String poName;

    @Column(name = "po_total")
    private String poTotal;

    @Column(name = "po_count")
    private String poCount;

    @NotNull
    @Column(nullable = false, name = "po_status")
    private String poStatus;

    @Column(name = "notes")
    private String notes;

    @Column(name = "user_id")
    private String userId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> approvers;

    @ElementCollection
    @BatchSize(size = 10)
    private List<String> attachments;


}
