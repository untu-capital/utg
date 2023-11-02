package com.untucapital.usuite.utg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int id;
    private int officeId;
    private String officeName;
    private boolean isLoanOfficer;
    private boolean isActive;
    private int userId;
    private int tellerId;
    private String firstname;
    private String lastname;
    private String displayName;
    private List<Integer> joiningDate;

    /**
     * @author tjchidanika
     * @created 7/9/2023
     */

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Department {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;
    }

    /**
     * @author tjchidanika
     * @created 11/9/2023
     */

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class POSCategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;

        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

    }

    /**
     * @author tjchidanika
     * @created 5/9/2023
     */
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class POSSupplier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;

        @Column(name = "address")
        private String address;

        @Column(name = "phone")
        private String phone;

        @Column(name = "contact_person")
        private String contactPerson;

        @Column(name = "comment")
        private String comment;

        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

    }
}
