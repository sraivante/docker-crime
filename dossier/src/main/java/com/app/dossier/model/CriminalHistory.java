package com.app.dossier.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Lombok annotations for reduced boilerplate (Assuming you use Lombok)
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity class for the 'criminal_history' table.
 */
@Entity
@Table(name = "criminal_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriminalHistory {

    // id BIGINT PRIMARY KEY AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * FOREIGN KEY (dossier_id) REFERENCES person_dossier(id)
     * Many-to-One relationship with the PersonDossier entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_id", nullable = false)
    private PersonDossier personDossier; // Assumes PersonDossier class exists

    // case_number VARCHAR(50)
    @Column(name = "case_number", length = 50)
    private String caseNumber;

    // year INT
    @Column(name = "year")
    private Integer year;

    // sections VARCHAR(200)
    @Column(name = "sections", length = 200)
    private String sections;

    // police_station VARCHAR(100)
    @Column(name = "police_station", length = 100)
    private String policeStation;

    // district VARCHAR(100)
    @Column(name = "district", length = 100)
    private String district;

    // co_accused_details TEXT
    @Column(name = "co_accused_details", columnDefinition = "TEXT")
    private String coAccusedDetails;

    // created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // --- Lifecycle Callbacks (for auto-managed timestamps) ---

    // Sets 'createdAt' before the entity is first persisted
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now(); // Set both on creation
    }

    // Sets 'updatedAt' before the entity is updated
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}