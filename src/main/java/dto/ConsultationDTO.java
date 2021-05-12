package dto;

import lombok.Data;
import model.Doctor;
import model.Patient;

import java.util.Date;
@Data
public class ConsultationDTO {

    private long id;
    private Date date;
    private String medicationName;
    private double dosage;
    private String comment;
    private Patient patient;
    private Doctor doctor;
}
