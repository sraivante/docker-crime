package com.app.dossier.repository;

import com.app.dossier.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    List<Associate> findByPersonDossierId(Long id);
}

