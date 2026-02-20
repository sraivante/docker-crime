package com.app.dossier.service;

import com.app.dossier.model.PersonDossier;
import com.app.dossier.repository.PersonDossierRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDossierService {

    private final PersonDossierRepository repository;

    // Constructor Injection is preferred
    @Autowired
    public PersonDossierService(PersonDossierRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public PersonDossier createDossier(PersonDossier dossier) {
        // Business logic (e.g., validation) would go here
        return repository.save(dossier);
    }

    // READ (All)
    public List<PersonDossier> findAllDossiers() {
        return repository.findAll();
    }

    // READ (By ID)
    public Optional<PersonDossier> findDossierById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public PersonDossier updateDossier(Long id, PersonDossier updatedDossier) {
        return repository.findById(id).map(dossier -> {
            // Only update fields you want to allow changing, then save.
            dossier.setFullName(updatedDossier.getFullName());
            dossier.setCurrentAddress(updatedDossier.getCurrentAddress());
            dossier.setPhoneNew(updatedDossier.getPhoneNew());
            // ... set other fields

            // The @PreUpdate method in the entity will handle the updatedAt timestamp
            return repository.save(dossier);
        }).orElseThrow(() -> new RuntimeException("Dossier not found with ID: " + id));
    }

    // DELETE
    public void deleteDossier(Long id) {
        repository.deleteById(id);
    }
}