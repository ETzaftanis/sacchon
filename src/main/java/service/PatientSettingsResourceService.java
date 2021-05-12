package service;

import jpaUtil.JpaUtil;
import model.Patient;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;

public class PatientSettingsResourceService {


    public void updatePatient(PatientRepresentation patientRepresentation, long id) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepresentation.createPatient();
        Patient oldPatient = patientRepository.read(id);
        em.detach(patient);
        patient.setId(id);
        patient.setDateRegistered(oldPatient.getDateRegistered());
        patientRepository.update(patient);
    }

    public PatientRepresentation getPatient(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);
        PatientRepresentation patientRepresentation = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation;
    }

    public void deletePatient(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.delete(patientRepository.read(id).getId());
    }
}
