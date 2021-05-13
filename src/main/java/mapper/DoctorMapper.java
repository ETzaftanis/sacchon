package mapper;

import dto.DoctorDTO;
import model.Doctor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.DoctorRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    public List<DoctorDTO> getAllDoctorDTO() {
        return doctorRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(doctor, DoctorDTO.class);
    }

    private Doctor convertToEntity(DoctorDTO doctorDTO) throws ParseException {

        return modelMapper.map(doctorDTO, Doctor.class);
    }

}
