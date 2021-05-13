package mapper;

import dto.ConsultationDTO;
import model.Consultation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.ConsultationRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultationMapper {

    private ConsultationRepository consultationRepository;

    private ModelMapper modelMapper;

    public List<ConsultationDTO> getAllDoctorDTO() {
        return consultationRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConsultationDTO convertToDTO(Consultation consultation) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(consultation, ConsultationDTO.class);
    }

    private Consultation convertToEntity(ConsultationDTO consultationDTO) throws ParseException {
        return modelMapper.map(consultationDTO, Consultation.class);
    }
}
