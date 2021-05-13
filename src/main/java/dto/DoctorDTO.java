package dto;

import lombok.Data;
import model.Consultation;
import model.Patient;

import java.util.Date;
import java.util.List;

@Data
public class DoctorDTO extends UserDTO {

    private long id;
    private Date recentConsultation;
    private List<Consultation> consultationList;
    private List<Patient> patientList;
}
