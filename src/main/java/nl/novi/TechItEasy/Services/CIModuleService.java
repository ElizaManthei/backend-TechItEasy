package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.CIModule;
import nl.novi.TechItEasy.Repositories.CIModuleRepository;
import nl.novi.TechItEasy.dtos.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepository;

    public CIModuleService (CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }
    public CIModuleDto saveCIModule(CIModuleInputDto ciModuleInputDto) {
      CIModuleDto ciModuleDto = new CIModuleDto();
      CIModule newCIModule = new CIModule();
      newCIModule = ciModuleInputDto.toCIModule(newCIModule);
      ciModuleRepository.save(newCIModule);
      return ciModuleDto.fromCIModule(newCIModule);
}

public List<CIModuleDto> getAllCIModules() {
        CIModuleDto ciModuleDto = new CIModuleDto();
        return ciModuleDto.fromCIModuleList(ciModuleRepository.findAll());
}

public CIModuleDto getCIModule(Long id) {
        CIModule ciModuleFound = ciModuleRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("CI Module not found"));
        CIModuleDto ciModuleDto = new CIModuleDto();
        return ciModuleDto.fromCIModule(ciModuleFound);
}

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto ciModuleInputDto) {
        CIModule ciModuleFound = ciModuleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find CI module"));
        CIModule updatedCiModule = ciModuleInputDto.toCIModule(ciModuleFound);
        ciModuleRepository.save(updatedCiModule);
        CIModuleDto ciModuleDto = new CIModuleDto();
        return ciModuleDto.fromCIModule(updatedCiModule);
    }

    public void deleteCIModule(Long id) {
        if (!ciModuleRepository.existsById(id))
            throw new RecordNotFoundException("Cannot find CI Module");
        ciModuleRepository.deleteById(id);
    }
}
