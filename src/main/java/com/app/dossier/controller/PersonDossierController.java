package com.app.dossier.controller;

import com.app.dossier.model.*;
import com.app.dossier.repository.*;
import com.app.dossier.service.PersonDossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dossiers")
public class PersonDossierController {

    @Autowired
      PersonDossierService personDossierService;
    @Autowired
      AssociateRepository associateRepository;
    @Autowired
    FamilyDetailRepository familyDetailRepository;
    @Autowired
    private LegalContactRepository legalContactRepository;
    @Autowired
    private CoAccusedRepository coAccusedRepository;
    @Autowired
    private CriminalHistoryRepository criminalHistoryRepository;
    @Autowired
    private CrimeVehicleRepository crimeVehicleRepository;
    @Autowired
    private CrimeWeaponRepository crimeWeaponRepository;
    @Autowired
    private CrimePropertyRepository crimePropertyRepository;
    @Autowired
    private FamilyCompanyRepository familyCompanyRepository;
    @Autowired
    private IdentifyingOfficerRepository identifyingOfficerRepository;
    @Autowired
    private NewBusinessRepository newBusinessRepository;




    // 1. CREATE (POST /api/v1/dossiers)
    @PostMapping
    public ResponseEntity<PersonDossier> createDossier(@RequestBody PersonDossier dossier) {
        PersonDossier createdDossier = personDossierService.createDossier(dossier);
        return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
    }

    @PostMapping("/fullDossier")
    public ResponseEntity<DossierResponseVO> createFullDossier(@RequestBody DossierRequestVO dossierRequestVO) {
        DossierResponseVO createdDossier = new DossierResponseVO();
         PersonDossier personDossier = dossierRequestVO.getPersonDossier();
        personDossier =  personDossierService.createDossier(personDossier);

        List<Associate> associates = dossierRequestVO.getAssociate();
        if(ObjectUtils.isEmpty(associates)){
            associates = new ArrayList<>();
        }
        for(Associate associate: associates) {
            associate.setPersonDossier(personDossier);
            associate = associateRepository.save(associate);
        }

        List<FamilyDetail> familyDetails = dossierRequestVO.getFamilyDetail();
        if(ObjectUtils.isEmpty(familyDetails)){
            familyDetails = new ArrayList<>();
        }
        for(FamilyDetail familyDetail: familyDetails){
            familyDetail.setPersonDossier(personDossier);
            familyDetail = familyDetailRepository.save(familyDetail);
        }


        List<LegalContact> legalContacts = dossierRequestVO.getLegalContact();
        if(ObjectUtils.isEmpty(legalContacts)){
            legalContacts = new ArrayList<>();
        }
        for(LegalContact legalContact: legalContacts) {
            legalContact.setPersonDossier(personDossier);
            legalContact = legalContactRepository.save(legalContact);
        }

        List<CoAccused> coAccuseds = dossierRequestVO.getCoAccused();
        if(ObjectUtils.isEmpty(coAccuseds)){
            coAccuseds = new ArrayList<>();
        }
        for(CoAccused coAccused: coAccuseds) {
            coAccused.setPersonDossier(personDossier);
            coAccused = coAccusedRepository.save(coAccused);
        }

        List<CriminalHistory> criminalHistorys = dossierRequestVO.getCriminalHistory();
        if(ObjectUtils.isEmpty(criminalHistorys)){
            criminalHistorys = new ArrayList<>();
        }
        for(CriminalHistory criminalHistory: criminalHistorys) {
            criminalHistory.setPersonDossier(personDossier);
            criminalHistory = criminalHistoryRepository.save(criminalHistory);
        }

        List<CrimeVehicle> crimeVehicles = dossierRequestVO.getCrimeVehicle();
        if(ObjectUtils.isEmpty(crimeVehicles)){
            crimeVehicles = new ArrayList<>();
        }
        for(CrimeVehicle crimeVehicle: crimeVehicles) {
            crimeVehicle.setPersonDossier(personDossier);
            crimeVehicle = crimeVehicleRepository.save(crimeVehicle);
        }

        List<CrimeWeapon> crimeWeapons = dossierRequestVO.getCrimeWeapon();
        if(ObjectUtils.isEmpty(crimeWeapons)){
            crimeWeapons = new ArrayList<>();
        }
        for(CrimeWeapon crimeWeapon: crimeWeapons) {
            crimeWeapon.setPersonDossier(personDossier);
            crimeWeapon = crimeWeaponRepository.save(crimeWeapon);
        }

        List<CrimeProperty> crimePropertys = dossierRequestVO.getCrimeProperty();
        if(ObjectUtils.isEmpty(crimePropertys)){
            crimePropertys = new ArrayList<>();
        }
        for(CrimeProperty crimeProperty: crimePropertys) {
            crimeProperty.setPersonDossier(personDossier);
            crimeProperty = crimePropertyRepository.save(crimeProperty);
        }

        List<FamilyCompany> familyCompanys = dossierRequestVO.getFamilyCompany();
        if(ObjectUtils.isEmpty(familyCompanys)){
            familyCompanys = new ArrayList<>();
        }
        for(FamilyCompany familyCompany: familyCompanys) {
            familyCompany.setPersonDossier(personDossier);
            familyCompany = familyCompanyRepository.save(familyCompany);
        }

        List<IdentifyingOfficer> identifyingOfficers = dossierRequestVO.getIdentifyingOfficer();
        if(ObjectUtils.isEmpty(identifyingOfficers)){
            identifyingOfficers = new ArrayList<>();
        }
        for(IdentifyingOfficer identifyingOfficer: identifyingOfficers) {
            identifyingOfficer.setPersonDossier(personDossier);
            identifyingOfficer = identifyingOfficerRepository.save(identifyingOfficer);
        }

        List<NewBusiness> newBusinesss = dossierRequestVO.getNewBusiness();
            if(ObjectUtils.isEmpty(newBusinesss)){
                newBusinesss = new ArrayList<>();
            }
            for(NewBusiness newBusiness: newBusinesss) {
                newBusiness.setPersonDossier(personDossier);
                newBusiness = newBusinessRepository.save(newBusiness);
            }


        createdDossier.setPersonDossier(personDossier);
        createdDossier.setAssociate(associates);
        createdDossier.setFamilyDetail(familyDetails);
        createdDossier.setLegalContact(legalContacts);
        createdDossier.setCoAccused(coAccuseds);
        createdDossier.setCriminalHistory(criminalHistorys);
        createdDossier.setCrimeVehicle(crimeVehicles);
        createdDossier.setCrimeWeapon(crimeWeapons);
        createdDossier.setCrimeProperty(crimePropertys);
        createdDossier.setFamilyCompany(familyCompanys);
        createdDossier.setIdentifyingOfficer(identifyingOfficers);
        createdDossier.setNewBusiness(newBusinesss);


        return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
    }


    // 2. READ All (GET /api/v1/dossiers)
    @GetMapping
    public ResponseEntity<List<PersonDossier>> getAllDossiers() {
        List<PersonDossier> dossiers = personDossierService.findAllDossiers();
        return ResponseEntity.ok(dossiers);
    }


    // 3. UPDATE (PUT /api/v1/dossiers/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PersonDossier> updateDossier(@PathVariable Long id, @RequestBody PersonDossier updatedDossier) {
        try {
            PersonDossier result = personDossierService.updateDossier(id, updatedDossier);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            // Handle the case where the dossier wasn't found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierResponseVO> getFullDossierById(@PathVariable Long id) {
        DossierResponseVO dossierResponseVO = new DossierResponseVO();
        PersonDossier presonDossier = personDossierService.findDossierById(id)
                .map(ResponseEntity::ok) // If found, return 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody(); // If not found, return 404 Not Found
        dossierResponseVO.setPersonDossier(presonDossier);

        List<Associate> associate = associateRepository.findByPersonDossierId(id);
        dossierResponseVO.setAssociate(associate);
        List<LegalContact> legalContacts = legalContactRepository.findByPersonDossierId(id);
        dossierResponseVO.setLegalContact(legalContacts);
        List<FamilyDetail> familyDetails = familyDetailRepository.findByPersonDossierId(id);
        dossierResponseVO.setFamilyDetail(familyDetails);

        List<CoAccused> coAccuseds = coAccusedRepository.findByPersonDossierId(id);
        dossierResponseVO.setCoAccused(coAccuseds);
        List<CriminalHistory> criminalHistorys = criminalHistoryRepository.findByPersonDossierId(id);
        dossierResponseVO.setCriminalHistory(criminalHistorys);
        List<CrimeVehicle> crimeVehicles = crimeVehicleRepository.findByPersonDossierId(id);
        dossierResponseVO.setCrimeVehicle(crimeVehicles);
        List<CrimeWeapon> crimeWeapons = crimeWeaponRepository.findByPersonDossierId(id);
        dossierResponseVO.setCrimeWeapon(crimeWeapons);
        List<CrimeProperty> crimePropertys = crimePropertyRepository.findByPersonDossierId(id);
        dossierResponseVO.setCrimeProperty(crimePropertys);
        List<FamilyCompany> familyCompanys = familyCompanyRepository.findByPersonDossierId(id);
        dossierResponseVO.setFamilyCompany(familyCompanys);
        List<IdentifyingOfficer> identifyingOfficers = identifyingOfficerRepository.findByPersonDossierId(id);
        dossierResponseVO.setIdentifyingOfficer(identifyingOfficers);
        List<NewBusiness> newBusinesss = newBusinessRepository.findByPersonDossierId(id);
        dossierResponseVO.setNewBusiness(newBusinesss);


        return ResponseEntity.ok(dossierResponseVO);

    }

    // 4. DELETE (DELETE /api/v1/dossiers/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        try {
            personDossierService.deleteDossier(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } catch (Exception e) {
            // In a real application, you might check if the ID existed first.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}