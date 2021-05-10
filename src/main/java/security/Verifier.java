package security;

import jpaUtil.JpaUtil;
import model.ChiefDoctor;
import model.Doctor;
import model.User;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;
import repository.ChiefDoctorRepository;
import repository.DoctorRepository;
import repository.PatientRepository;

import javax.persistence.EntityManager;

public class Verifier extends SecretVerifier {

    public int verify(String username, char[] password) {

        EntityManager em = JpaUtil.getEntityManager();

        verifyByRole(getUserByUsername(username, em), username, password, em);

        em.close();

        return SecretVerifier.RESULT_VALID;
    }

    private int verifyByRole(User user, String username,
                             char[] password, EntityManager em) {

        if (user.getUsername().equals(username)) {
            String passwordInDb = user.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(user.getRole()));
                em.close();
                return SecretVerifier.RESULT_VALID;
            }
        }

        return SecretVerifier.RESULT_INVALID;
    }

    private User getUserByUsername(String username, EntityManager em) {

        PatientRepository patientRepository = new PatientRepository(em);
        User patient = patientRepository.getByUsername(username);

        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.getByUsername(username);

        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        ChiefDoctor chiefDoctor = chiefDoctorRepository.getByUsername(username);

        return getUserByUsername(patient, doctor, chiefDoctor);
    }

    private User getUserByUsername(User patient, Doctor doctor, ChiefDoctor chiefDoctor) {

        if (patient != null) {
            return patient;
        } else if (doctor != null) {
            return doctor;
        } else if (chiefDoctor != null) {
            return chiefDoctor;
        }
        return new User();
    }

}
