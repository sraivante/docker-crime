package com.app.dossier.repository;

import com.app.dossier.model.PersonDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Assumes the PersonDossier entity class is in the correct package
@Repository
public interface PersonDossierRepository extends JpaRepository<PersonDossier, Long> {

    // You can define custom query methods here, e.g.:
    // List<PersonDossier> findByFullNameContainingIgnoreCase(String name);
    // Optional<PersonDossier> findByAadhaarNumber(String aadhaar);

}