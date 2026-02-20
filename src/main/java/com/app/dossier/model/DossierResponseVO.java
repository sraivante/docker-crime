package com.app.dossier.model;

import lombok.Data;

import java.util.List;

@Data
public class DossierResponseVO {
    private PersonDossier personDossier;
    private List<Associate> associate;
    private List<FamilyDetail> familyDetail;

    //mahila mitra sasural ka vivran
    private List<LegalContact> legalContact;
    private List<CoAccused> coAccused;
    private List<CriminalHistory> criminalHistory;
    private List<CrimeVehicle> crimeVehicle;
    private List<CrimeWeapon> crimeWeapon;
    private List<CrimeProperty> crimeProperty;
    private List<FamilyCompany> familyCompany;
    private List<IdentifyingOfficer> identifyingOfficer;
    private List<NewBusiness> newBusiness;

}
