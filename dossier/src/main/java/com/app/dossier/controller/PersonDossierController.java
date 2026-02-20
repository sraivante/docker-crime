package com.app.dossier.controller;

import com.app.dossier.model.PersonDossier;
import com.app.dossier.service.PersonDossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dossiers")
public class PersonDossierController {

    private final PersonDossierService service;

    @Autowired
    public PersonDossierController(PersonDossierService service) {
        this.service = service;
    }

    // 1. CREATE (POST /api/v1/dossiers)
    @PostMapping
    public ResponseEntity<PersonDossier> createDossier(@RequestBody PersonDossier dossier) {
        PersonDossier createdDossier = service.createDossier(dossier);
        return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
    }

    // 2. READ All (GET /api/v1/dossiers)
    @GetMapping
    public ResponseEntity<List<PersonDossier>> getAllDossiers() {
        List<PersonDossier> dossiers = service.findAllDossiers();
        return ResponseEntity.ok(dossiers);
    }

    // 2. READ By ID (GET /api/v1/dossiers/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PersonDossier> getDossierById(@PathVariable Long id) {
        return service.findDossierById(id)
                .map(ResponseEntity::ok) // If found, return 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    // 3. UPDATE (PUT /api/v1/dossiers/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PersonDossier> updateDossier(@PathVariable Long id, @RequestBody PersonDossier updatedDossier) {
        try {
            PersonDossier result = service.updateDossier(id, updatedDossier);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            // Handle the case where the dossier wasn't found
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE (DELETE /api/v1/dossiers/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        try {
            service.deleteDossier(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } catch (Exception e) {
            // In a real application, you might check if the ID existed first.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}