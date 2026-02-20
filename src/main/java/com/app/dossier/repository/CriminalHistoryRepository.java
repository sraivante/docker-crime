package com.app.dossier.repository;

import com.app.dossier.model.CriminalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriminalHistoryRepository extends JpaRepository<CriminalHistory, Long> {

    List<CriminalHistory> findByPersonDossierId(Long id);
}

