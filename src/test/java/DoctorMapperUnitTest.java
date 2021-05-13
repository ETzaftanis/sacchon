import dto.DoctorDTO;
import model.Doctor;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorMapperUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertDoctorEntityToDoctorDto_thenCorrect() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setAddress("Test address 1");
        doctor.setRecentConsultation(new Date(1997, 8, 26));
        doctor.setEmail("test@gmail.com");
        doctor.setName("John");

        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        assertEquals(doctor.getId(), doctorDTO.getId());
        assertEquals(doctor.getEmail(), doctorDTO.getEmail());
        assertEquals(doctor.getAddress(), doctorDTO.getAddress());
        assertEquals(doctor.getRecentConsultation(), doctorDTO.getRecentConsultation());
        assertEquals(doctor.getName(), doctorDTO.getName());
    }

    @Test
    public void whenConvertDoctorDTOToDoctorEntity_thenCorrect() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setAddress("Test address 1");
        doctorDTO.setRecentConsultation(new Date(1997, 8, 26));
        doctorDTO.setEmail("test@gmail.com");
        doctorDTO.setName("John");

        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        assertEquals(doctorDTO.getId(), doctor.getId());
        assertEquals(doctorDTO.getEmail(), doctor.getEmail());
        assertEquals(doctorDTO.getAddress(), doctor.getAddress());
        assertEquals(doctorDTO.getRecentConsultation(), doctor.getRecentConsultation());
        assertEquals(doctorDTO.getName(), doctor.getName());
    }
}
