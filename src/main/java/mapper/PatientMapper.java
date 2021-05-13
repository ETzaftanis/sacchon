package mapper;

import dto.PatientDTo;
import model.Patient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.PatientRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {

    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    public List<PatientDTo> getAllPatientDTO() {
        return patientRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PatientDTo convertToDTO(Patient patient) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(patient, PatientDTo.class);
    }

    private Patient convertToEntity(PatientDTo patientDTo) throws ParseException {
        return modelMapper.map(patientDTo, Patient.class);
    }
}
