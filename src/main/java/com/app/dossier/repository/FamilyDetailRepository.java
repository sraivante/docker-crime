package com.app.dossier.repository;

import com.app.dossier.model.FamilyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyDetailRepository extends JpaRepository<FamilyDetail, Long> {

    List<FamilyDetail> findByPersonDossierId(Long id);
}

