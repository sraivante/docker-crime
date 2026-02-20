package com.app.dossier.repository;

import com.app.dossier.model.CrimeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeVehicleRepository extends JpaRepository<CrimeVehicle, Long> {

    List<CrimeVehicle> findByPersonDossierId(Long id);
}

