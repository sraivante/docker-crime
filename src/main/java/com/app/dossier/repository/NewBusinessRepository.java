package com.app.dossier.repository;

import com.app.dossier.model.NewBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewBusinessRepository extends JpaRepository<NewBusiness, Long> {

    List<NewBusiness> findByPersonDossierId(Long id);
}

