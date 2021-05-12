package service;

import jpaUtil.JpaUtil;
import repository.PatientRepository;
import resource.ResourceUtils;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class PatientCarbDailyAverageResourceService {

    public static List<Double> getAverageCarb(long id, String start, String end) {

        Date dateStart = ResourceUtils.stringToDate(start, -1);
        Date dateEnd = ResourceUtils.stringToDate(end, 1);

        EntityManager em = JpaUtil.getEntityManager();

        PatientRepository patientRepository = new PatientRepository(em);
        List<Double> carbList = patientRepository.getCarbAverageList(id, dateStart, dateEnd);

        em.close();
        return carbList;
    }
}
