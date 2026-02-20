package com.app.dossier.repository;

import com.app.dossier.model.FamilyCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyCompanyRepository extends JpaRepository<FamilyCompany, Long> {

    List<FamilyCompany> findByPersonDossierId(Long id);
}

