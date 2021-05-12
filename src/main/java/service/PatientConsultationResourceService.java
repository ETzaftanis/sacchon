package service;

import jpaUtil.JpaUtil;
import model.Consultation;
import repository.PatientRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientConsultationResourceService {

    public static ConsultationRepresentation getConsultation(long patientId,long consultationId) {
        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Consultation> consultationList = patientRepository.getConsultationList(patientId);
        Consultation consultation = new Consultation();
        for (Consultation c : consultationList) {
            if (c.getId() == consultationId) {
                consultation = c;
            }
        }
        ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation;
    }
}
