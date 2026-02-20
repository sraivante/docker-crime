package com.app.dossier.repository;

import com.app.dossier.model.LegalContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalContactRepository extends JpaRepository<LegalContact, Long> {

    List<LegalContact> findByPersonDossierId(Long id);
}

