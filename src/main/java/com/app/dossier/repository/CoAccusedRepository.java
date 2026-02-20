package com.app.dossier.repository;

import com.app.dossier.model.CoAccused;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoAccusedRepository extends JpaRepository<CoAccused, Long> {

    List<CoAccused> findByPersonDossierId(Long id);
}

