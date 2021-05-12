package service;

import jpaUtil.JpaUtil;
import model.Glucose;
import model.Patient;
import repository.GlucoseRepository;
import repository.PatientRepository;
import representation.GlucoseRepresentation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientGlucoseListResourceService {

    public static List<GlucoseRepresentation> getGlucoseRepresentations(long patientId) {
        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Glucose> glucoseList = patientRepository.getGlucoseList(patientId);
        List<GlucoseRepresentation> glucoseRepresentationList = new ArrayList<>();

        for (Glucose c : glucoseList) {
            glucoseRepresentationList.add(new GlucoseRepresentation(c));
        }

        em.close();
        return glucoseRepresentationList;
    }

    public static GlucoseRepresentation getGlucoseRepresentation(GlucoseRepresentation glucoseRepresentationIn, long patientId) {
        if (glucoseRepresentationIn == null) return new GlucoseRepresentation();

        glucoseRepresentationIn.setPatientId(patientId);
        Glucose glucose = glucoseRepresentationIn.createGlucose();
        EntityManager em = JpaUtil.getEntityManager();
        GlucoseRepository glucoseRepository = new GlucoseRepository(em);
        glucoseRepository.save(glucose);
        GlucoseRepresentation g = new GlucoseRepresentation(glucose);

        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(patientId);
        em.detach(patient);
        patient.setRecentGlucose(glucose.getDate());
        patientRepository.update(patient);

        em.close();
        return g;
    }
}
