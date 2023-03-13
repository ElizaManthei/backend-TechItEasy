package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.Television;
import nl.novi.TechItEasy.Repositories.TelevisionRepository;
import nl.novi.TechItEasy.dtos.TelevisionDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {this.televisionRepository = televisionRepository;}

    public List<Television> getTelevisions(){
        return televisionRepository.findAll();
    }

    public TelevisionDto getTelevision(Long id){
        Television televisionFound = televisionRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Cannot find television"));
         TelevisionDto televisionDto = new TelevisionDto();
         return televisionDto.fromTelevision(televisionFound);
    }

    public Long saveTelevision(TelevisionInputDto televisionInputDto ){
       Television television = televisionInputDto.toTelevision();
       televisionRepository.save(television);
       return television.getId();
    }

    public void updateTelevision(Long id, TelevisionInputDto televisionInputDto){
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find television"));
        television  = televisionInputDto.toTelevision();
        television.setId(id);
        televisionRepository.save(television);
    }
    public void deleteTelevision(Long id){
        if (!televisionRepository.existsById(id))
            throw new RecordNotFoundException("Cannot find television");
        televisionRepository.deleteById(id);
    }
}
