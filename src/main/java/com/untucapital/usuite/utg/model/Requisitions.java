package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
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
    private List<String> approvers;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> attachments;

}
