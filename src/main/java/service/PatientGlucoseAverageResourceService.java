package service;

import jpaUtil.JpaUtil;
import repository.PatientRepository;
import resource.ResourceUtils;

import javax.persistence.EntityManager;
import java.util.Date;

public class PatientGlucoseAverageResourceService {

    public static Double getAverageGlucose(long patientId, String start, String end) {

        Date dateStart = ResourceUtils.stringToDate(start, -1);
        Date dateEnd = ResourceUtils.stringToDate(end, 1);

        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        Double glucose = patientRepository.getGlucoseAverage(patientId, dateStart, dateEnd);

        em.close();
        return glucose;
    }
}
