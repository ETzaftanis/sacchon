package service;

import jpaUtil.JpaUtil;
import model.Carb;
import model.Patient;
import repository.CarbRepository;
import repository.PatientRepository;
import representation.CarbRepresentation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientCarbListResourceService {

    public static List<CarbRepresentation> getCarbList(long patientId) {
        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Carb> carbList = patientRepository.getCarbList(patientId);
        List<CarbRepresentation> carbRepresentationList = new ArrayList<>();

        for (Carb c : carbList) {
            carbRepresentationList.add(new CarbRepresentation(c));
        }

        em.close();
        return carbRepresentationList;
    }

    public static CarbRepresentation getCarbRepresentation(CarbRepresentation carbRepresentationIn, long patientId) {
        if (carbRepresentationIn == null) return null;

        carbRepresentationIn.setPatientId(patientId);
        Carb carb = carbRepresentationIn.createCarb();
        EntityManager em = JpaUtil.getEntityManager();
        CarbRepository carbRepository = new CarbRepository(em);
        carbRepository.save(carb);
        CarbRepresentation c = new CarbRepresentation(carb);

        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(patientId);

        em.detach(patient);
        patient.setRecentCarb(carb.getDate());
        patientRepository.update(patient);

        em.close();
        return c;
    }

}
