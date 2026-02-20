package com.app.dossier.repository;

import com.app.dossier.model.CrimeWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeWeaponRepository extends JpaRepository<CrimeWeapon, Long> {


    List<CrimeWeapon> findByPersonDossierId(Long id);
}

