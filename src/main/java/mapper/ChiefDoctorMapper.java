package mapper;

import dto.ChiefDoctorDTO;
import model.ChiefDoctor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.ChiefDoctorRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ChiefDoctorMapper {

    private ChiefDoctorRepository chiefDoctorRepository;

    private ModelMapper modelMapper;

    public List<ChiefDoctorDTO> getAllCarbDTO() {
        return chiefDoctorRepository
                .findAll()
                .stream()
                .map(this::convertToCarbDTO)
                .collect(Collectors.toList());
    }

    private ChiefDoctorDTO convertToCarbDTO(ChiefDoctor chiefDoctor) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(chiefDoctor, ChiefDoctorDTO.class);
    }

    private ChiefDoctor convertToEntity(ChiefDoctorDTO chiefDoctorDTO) throws ParseException {
        return modelMapper.map(chiefDoctorDTO, ChiefDoctor.class);
    }
}
