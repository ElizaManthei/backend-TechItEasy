package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.RemoteController;
import nl.novi.TechItEasy.Repositories.RemoteControllerRepository;
import nl.novi.TechItEasy.dtos.RemoteControllerDto;
import nl.novi.TechItEasy.dtos.RemoteControllerInputDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public RemoteControllerDto saveRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        RemoteController newRemoteController = new RemoteController();
        newRemoteController = remoteControllerInputDto.toRemoteController(newRemoteController);
        remoteControllerRepository.save(newRemoteController);
        return  remoteControllerDto.fromRemoteController(newRemoteController);
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        return remoteControllerDto.fromRemoteControllerList(remoteControllerRepository.findAll());
    }

    public RemoteControllerDto getRemoteControllerById(Long id) {
        RemoteController remoteController = remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller not found"));
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        return remoteControllerDto.fromRemoteController(remoteController);
    }
    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteControllerFound = remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find television"));
        RemoteController updatedTRemoteController = remoteControllerInputDto.toRemoteController(remoteControllerFound);
        remoteControllerRepository.save(updatedTRemoteController);
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        return remoteControllerDto.fromRemoteController(updatedTRemoteController);
    }
    public void deleteRemoteController(Long id) {
        if (!remoteControllerRepository.existsById(id))
            throw new RecordNotFoundException("Cannot find remote controller");
        remoteControllerRepository.deleteById(id);
    }
}
