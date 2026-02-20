package com.app.dossier.model;

import jakarta.persistence.*; // Use 'javax.persistence.*' for older JPA/Jakarta EE versions
import java.time.LocalDateTime;

// Lombok annotations for reduced boilerplate
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity class for the 'family_details' table.
 */
@Entity
@Table(name = "family_details")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with no arguments
@AllArgsConstructor // Generates a constructor with all arguments
public class FamilyDetail {

    // id BIGINT PRIMARY KEY AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * FOREIGN KEY (dossier_id) REFERENCES person_dossier(id)
     * This establishes a Many-to-One relationship with the PersonDossier entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading is usually better for performance
    @JoinColumn(name = "dossier_id", nullable = false) // Maps to the dossier_id column
    private PersonDossier personDossier;

    // relation VARCHAR(50)
    @Column(name = "relation", length = 50)
    private String relation;

    // name VARCHAR(150)
    @Column(name = "name", length = 150)
    private String name;

    // age INT
    @Column(name = "age")
    private Integer age;

    // address TEXT
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    // mobile VARCHAR(15)
    @Column(name = "mobile", length = 15)
    private String mobile;

    // occupation VARCHAR(100)
    @Column(name = "occupation", length = 100)
    private String occupation;

    // criminal_association_details TEXT
    @Column(name = "criminal_association_details", columnDefinition = "TEXT")
    private String criminalAssociationDetails;

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