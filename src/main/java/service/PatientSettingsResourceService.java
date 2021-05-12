package service;

import jpaUtil.JpaUtil;
import model.Patient;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;

public class PatientSettingsResourceService {

    public static PatientRepresentation updatePatient(PatientRepresentation patientRepresentation, long patientId) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepresentation.createPatient();
        Patient oldPatient = patientRepository.read(patientId);
        em.detach(patient);
        patient.setId(patientId);
        patient.setDateRegistered(oldPatient.getDateRegistered());
        patientRepository.update(patient);
        return patientRepresentation;
    }

    public static PatientRepresentation getPatient(long patientId) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(patientId);
        PatientRepresentation patientRepresentation = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation;
    }

    public static void deletePatient(long patientId) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.delete(patientRepository.read(patientId).getId());
    }
}
