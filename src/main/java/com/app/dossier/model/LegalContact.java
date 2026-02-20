package com.app.dossier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

// Lombok annotations for reduced boilerplate (Assuming you use Lombok)
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity class for the 'legal_contacts' table.
 */
@Entity
@Table(name = "legal_contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalContact {

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
    @JsonIgnore
    private PersonDossier personDossier; // Assumes PersonDossier class exists

    // name VARCHAR(150)
    @Column(name = "name", length = 150)
    private String name;

    // father_name_and_address TEXT
    @Column(name = "father_name_and_address", columnDefinition = "TEXT")
    private String fatherNameAndAddress;

    // relation_type ENUM('SURETY','GUARANTOR','ADVOCATE')
    //@Enumerated(EnumType.STRING) // Stores the enum name (e.g., "SURETY") as a String
    @Column(name = "relation_type", length = 50)
    private String relationType;

    // mobile VARCHAR(15)
    @Column(name = "mobile", length = 15)
    private String mobile;

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