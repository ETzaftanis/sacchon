package service;

import jpaUtil.JpaUtil;
import repository.PatientRepository;
import resource.ResourceUtils;

import javax.persistence.EntityManager;
import java.util.Date;

public class PatientCarbAverageResourceService {

    public static Double getAverageCarb(long id, String start, String end) {

        Date dateStart = ResourceUtils.stringToDate(start, -1);
        Date dateEnd = ResourceUtils.stringToDate(end, 1);

        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        Double carb = patientRepository.getCarbAverage(id, dateStart, dateEnd);

        em.close();
        return carb;
    }
}
