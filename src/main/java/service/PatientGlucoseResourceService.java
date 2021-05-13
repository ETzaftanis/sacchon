package service;

import jpaUtil.JpaUtil;
import model.Glucose;
import repository.GlucoseRepository;
import repository.PatientRepository;
import representation.GlucoseRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientGlucoseResourceService {

    public static GlucoseRepresentation getGlucose(long patientId, long glucoseId) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        List<Glucose> glucoseList = patientRepository.getGlucoseList(patientId);
        Glucose glucose = new Glucose();
        for (Glucose g : glucoseList) {
            if (g.getId() == glucoseId) {
                glucose = g;
            }
        }
        GlucoseRepresentation glucoseRepresentation = new GlucoseRepresentation(glucose);
        em.close();
        return glucoseRepresentation;
    }

    public static GlucoseRepresentation update(GlucoseRepresentation glucoseRepresentationIn, long glucoseId) {
        if (glucoseRepresentationIn == null) return new GlucoseRepresentation();

        EntityManager em = JpaUtil.getEntityManager();
        GlucoseRepository glucoseRepository = new GlucoseRepository(em);
        Glucose glucose = glucoseRepository.read(glucoseId);
        glucose.setGlucose(glucoseRepresentationIn.getGlucose());

        em.detach(glucose);
        glucose.setId(glucoseId);
        glucoseRepository.update(glucose);
        return glucoseRepresentationIn;
    }

    public static void delete(long glucoseId) {
        EntityManager em = JpaUtil.getEntityManager();
        GlucoseRepository glucoseRepository = new GlucoseRepository(em);
        glucoseRepository.delete(glucoseRepository.read(glucoseId).getId());
    }
}
