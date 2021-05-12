package service;

import jpaUtil.JpaUtil;
import model.Carb;
import repository.CarbRepository;
import repository.PatientRepository;
import representation.CarbRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientCarbResourceService {

    public static CarbRepresentation getCarb(long patientId, long carbId) {
        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Carb> carbList = patientRepository.getCarbList(patientId);
        Carb carb = new Carb();

        for (Carb c : carbList) {
            if (c.getId() == carbId) {
                carb = c;
            }
        }
        CarbRepresentation carbRepresentation = new CarbRepresentation(carb);
        em.close();
        return carbRepresentation;
    }

    public static CarbRepresentation updateCarb(CarbRepresentation carbRepresentation, long patientId, long carbId) {
        if (carbRepresentation == null) return new CarbRepresentation();

        carbRepresentation.setId(carbId);
        carbRepresentation.setPatientId(patientId);
        EntityManager em = JpaUtil.getEntityManager();
        CarbRepository carbRepository = new CarbRepository(em);
        Carb carb = carbRepresentation.createCarb();
        em.detach(carb);
        carb.setId(carbId);
        carbRepository.update(carb);
        return carbRepresentation;
    }

    public static void deleteCarb(long carbId) {
        EntityManager em = JpaUtil.getEntityManager();
        CarbRepository carbRepository = new CarbRepository(em);
        carbRepository.delete(carbRepository.read(carbId).getId());
    }
}
