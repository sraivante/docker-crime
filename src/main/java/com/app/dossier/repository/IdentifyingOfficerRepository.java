package com.app.dossier.repository;

import com.app.dossier.model.IdentifyingOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentifyingOfficerRepository extends JpaRepository<IdentifyingOfficer, Long> {

    List<IdentifyingOfficer> findByPersonDossierId(Long id);
}

