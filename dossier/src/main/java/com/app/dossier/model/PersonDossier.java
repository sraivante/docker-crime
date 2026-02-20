package com.app.dossier.model;

import jakarta.persistence.*; // Use 'javax.persistence.*' for older JPA/Jakarta EE versions
import java.time.LocalDate;
import java.time.LocalDateTime;

// Lombok annotations for reduced boilerplate
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity class for the 'person_dossier' table.
 */
@Entity
@Table(name = "person_dossier")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with no arguments
@AllArgsConstructor // Generates a constructor with all arguments
public class PersonDossier {

    // id BIGINT PRIMARY KEY AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // full_name VARCHAR(150)
    @Column(name = "full_name", length = 150)
    private String fullName;

    // father_name VARCHAR(150)
    @Column(name = "father_name", length = 150)
    private String fatherName;

    // current_address TEXT
    @Column(name = "current_address", columnDefinition = "TEXT")
    private String currentAddress;

    // permanent_address TEXT
    @Column(name = "permanent_address", columnDefinition = "TEXT")
    private String permanentAddress;

    // date_of_birth DATE
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // religion VARCHAR(50)
    @Column(name = "religion", length = 50)
    private String religion;

    // caste VARCHAR(50)
    @Column(name = "caste", length = 50)
    private String caste;

    // education VARCHAR(100)
    @Column(name = "education", length = 100)
    private String education;

    // unique_identification_mark VARCHAR(255)
    @Column(name = "unique_identification_mark")
    private String uniqueIdentificationMark;

    // phone_new VARCHAR(15)
    @Column(name = "phone_new", length = 15)
    private String phoneNew;

    // phone_old VARCHAR(15)
    @Column(name = "phone_old", length = 15)
    private String phoneOld;

    // aadhaar_number VARCHAR(20)
    @Column(name = "aadhaar_number", length = 20)
    private String aadhaarNumber;

    // pan_number VARCHAR(20)
    @Column(name = "pan_number", length = 20)
    private String panNumber;

    // passport_number VARCHAR(20)
    @Column(name = "passport_number", length = 20)
    private String passportNumber;

    // credit_card_details VARCHAR(100) - **Note: Consider security implications!**
    @Column(name = "credit_card_details", length = 100)
    private String creditCardDetails;

    // bank_details VARCHAR(200) - **Note: Consider security implications!**
    @Column(name = "bank_details", length = 200)
    private String bankDetails;

    // driving_license VARCHAR(30)
    @Column(name = "driving_license", length = 30)
    private String drivingLicense;

    // voter_id VARCHAR(30)
    @Column(name = "voter_id", length = 30)
    private String voterId;

    // other_identity_details TEXT
    @Column(name = "other_identity_details", columnDefinition = "TEXT")
    private String otherIdentityDetails;

    // email VARCHAR(100)
    @Column(name = "email", length = 100)
    private String email;

    // photo_url VARCHAR(255)
    @Column(name = "photo_url")
    private String photoUrl;

    // created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    @Column(name = "created_at", updatable = false) // 'updatable = false' ensures it's set only on creation
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