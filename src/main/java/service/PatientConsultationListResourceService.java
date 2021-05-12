package service;

import jpaUtil.JpaUtil;
import model.Consultation;
import repository.ConsultationRepository;
import repository.PatientRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientConsultationListResourceService {

    public static List<ConsultationRepresentation> getConsultationLIst(long id) {
        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Consultation> consultationList = patientRepository.getConsultationList(this.patientId);
        List<ConsultationRepresentation> consultationRepresentationList = new ArrayList<>();

        for (Consultation c : consultationList) {
            consultationRepresentationList.add(new ConsultationRepresentation(c));
        }

        em.close();
        return consultationRepresentationList;
    }

    public static ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn,long id) {
        if (consultationRepresentationIn == null) return null;

        consultationRepresentationIn.setPatientId(id);
        Consultation consultation = consultationRepresentationIn.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation c = new ConsultationRepresentation(consultation);
        return c;
    }
}
