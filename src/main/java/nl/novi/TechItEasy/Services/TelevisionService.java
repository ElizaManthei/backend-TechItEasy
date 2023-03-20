package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Exceptions.RecordAssignedException;
import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.CIModule;
import nl.novi.TechItEasy.Models.RemoteController;
import nl.novi.TechItEasy.Models.Television;
import nl.novi.TechItEasy.Models.WallBracket;
import nl.novi.TechItEasy.Repositories.CIModuleRepository;
import nl.novi.TechItEasy.Repositories.RemoteControllerRepository;
import nl.novi.TechItEasy.Repositories.TelevisionRepository;
import nl.novi.TechItEasy.Repositories.WallBracketRepository;
import nl.novi.TechItEasy.dtos.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;
    private final WallBracketRepository wallBracketRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<TelevisionDto> getTelevisions() {
        TelevisionDto televisionDto = new TelevisionDto();
        return televisionDto.fromTelevisionList(televisionRepository.findAll());
    }

    public TelevisionDto saveTelevision(TelevisionInputDto televisionInputDto) {
        TelevisionDto televisionDto = new TelevisionDto();
        Television newTelevision = new Television();
        newTelevision = televisionInputDto.toTelevision(newTelevision);
        televisionRepository.save(newTelevision);
        return televisionDto.fromTelevision(newTelevision);
    }

    public TelevisionDto getTelevisionById(Long id) {
        Television televisionFound = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find television"));
        TelevisionDto televisionDto = new TelevisionDto();
        return televisionDto.fromTelevision(televisionFound);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Television televisionFound = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find television"));
        Television updatedTelevision = televisionInputDto.toTelevision(televisionFound);
        televisionRepository.save(updatedTelevision);
        TelevisionDto televisionDto = new TelevisionDto();
        return televisionDto.fromTelevision(updatedTelevision);
        }

    public void deleteTelevision(Long id) {
        if (!televisionRepository.existsById(id))
            throw new RecordNotFoundException("Cannot find television");
        televisionRepository.deleteById(id);
    }

    public TelevisionDto assignRemoteControllerToTelevision(Long televisionId, IdInputDto remoteControllerInputId) {
        Television televisionFound = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Tv not found"));
        RemoteController remoteControllerFound = remoteControllerRepository.findById(remoteControllerInputId.id).orElseThrow(() -> new RecordNotFoundException("R C not found"));
        if (remoteControllerFound.getTelevision() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(remoteControllerFound.getName());
            sb.append(" is already assigned to ");
            sb.append(remoteControllerFound.getTelevision().getName());
            throw new RecordAssignedException(sb.toString());
        }
        televisionFound.setRemoteController(remoteControllerFound);
        remoteControllerFound.setTelevision(televisionFound);
        televisionRepository.save(televisionFound);
        remoteControllerRepository.save(remoteControllerFound);
        TelevisionDto televisionDto = new TelevisionDto();
        return televisionDto.fromTelevision(televisionFound);
    }

    public TelevisionDto assignCIModule(Long televisionId, IdInputDto cIModuleId) {
        Television televisionFound = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Tv not found"));
        CIModule ciModuleFound = ciModuleRepository.findById(cIModuleId.id).orElseThrow(() -> new RecordNotFoundException("CI Module not found"));
        List<Television> cIModuleTelevisionList = new ArrayList<>(ciModuleFound.getTelevisions());
        if ( televisionFound.getCiModule() != null) {
            if (televisionFound.getCiModule().getId().equals(cIModuleId.id)) {
                StringBuilder sb = new StringBuilder();
                sb.append(ciModuleFound.getName());
                sb.append(" is already assigned to ");
                sb.append(televisionFound.getName());
                throw new RecordAssignedException(sb.toString());
            }
        }

        televisionFound.setCiModule(ciModuleFound);
        cIModuleTelevisionList.add(televisionFound);
        ciModuleFound.setTelevisions(cIModuleTelevisionList);
        televisionRepository.save(televisionFound);
        ciModuleRepository.save(ciModuleFound);

        TelevisionDto televisionDto = new TelevisionDto();

        return televisionDto.fromTelevision(televisionFound);
    }

    public TelevisionDto assignWallBracket(Long televisionId, IdInputDto wallBracketId) {
        Television televisionFound = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Tv not found"));
        WallBracket wallBracketFound = wallBracketRepository.findById(wallBracketId.id).orElseThrow(() -> new RecordNotFoundException("Wall bracket not found"));
        for (WallBracket w : televisionFound.getWallBrackets()) {
            if (w.getId().equals(wallBracketId.id)) {
                StringBuilder sb = new StringBuilder();
                sb.append(w.getName());
                sb.append(" is already assigned to ");
                sb.append(televisionFound.getName());
                throw new RecordAssignedException(sb.toString());
            }
        }

        List<WallBracket> wallBracketList;
        List<Television> televisionList;

        televisionList = wallBracketFound.getTelevisions();
        televisionList.add(televisionFound);
        wallBracketList = televisionFound.getWallBrackets();
        wallBracketList.add(wallBracketFound);
        televisionFound.setWallBrackets(wallBracketList);
        wallBracketFound.setTelevisions(televisionList);
        wallBracketRepository.save(wallBracketFound);
        televisionRepository.save(televisionFound);

        TelevisionDto televisionDto = new TelevisionDto();
        return   televisionDto.fromTelevision(televisionFound);
    }
}

