package mapper;

import dto.CarbDTO;
import model.Carb;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import repository.CarbRepository;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class CarbMapper {

    private CarbRepository carbRepository;

    private ModelMapper modelMapper;

    public List<CarbDTO> getAllCarbDTO() {
        return carbRepository
                .findAll()
                .stream()
                .map(this::convertToCarbDTO)
                .collect(Collectors.toList());
    }

    private CarbDTO convertToCarbDTO(Carb carb) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(carb, CarbDTO.class);
    }

    private Carb convertToEntity(CarbDTO carbDTO) throws ParseException {
        return modelMapper.map(carbDTO, Carb.class);
    }

}
