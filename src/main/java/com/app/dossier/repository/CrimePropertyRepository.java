package com.app.dossier.repository;

import com.app.dossier.model.CrimeProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimePropertyRepository extends JpaRepository<CrimeProperty, Long> {

    List<CrimeProperty> findByPersonDossierId(Long id);
}

