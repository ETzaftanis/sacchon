package mapper;

import dto.GlucoseDTO;
import model.Glucose;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.GlucoseRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class GlucoseMapper {

    private GlucoseRepository glucoseRepository;

    private ModelMapper modelMapper;

    public List<GlucoseDTO> getAllPatientDTO() {
        return glucoseRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GlucoseDTO convertToDTO(Glucose glucose) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(glucose, GlucoseDTO.class);
    }

    private Glucose convertToEntity(GlucoseDTO glucoseDTO) throws ParseException {
        return modelMapper.map(glucoseDTO, Glucose.class);
    }
}
