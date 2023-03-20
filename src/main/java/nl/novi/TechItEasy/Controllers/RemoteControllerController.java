package nl.novi.TechItEasy.Controllers;

import jakarta.validation.Valid;
import nl.novi.TechItEasy.Services.RemoteControllerService;
import nl.novi.TechItEasy.dtos.CIModuleInputDto;
import nl.novi.TechItEasy.dtos.RemoteControllerDto;
import nl.novi.TechItEasy.dtos.RemoteControllerInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static nl.novi.TechItEasy.Controllers.TelevisionsController.getObjectResponseEntity;

@RestController
@RequestMapping("remotecontrollers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    protected RemoteControllerController (RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @PostMapping
    public ResponseEntity<Object> createRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult br){
        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;

        return ResponseEntity.created(null).body(remoteControllerService.saveRemoteController(remoteControllerInputDto));
    }

    @GetMapping
    public ResponseEntity<Object> getRemoteControllers(){
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getRemoteControllerById( @PathVariable Long id) {
        return ResponseEntity.ok(remoteControllerService.getRemoteControllerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable Long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto){
        return (ResponseEntity.ok().body(remoteControllerService.updateRemoteController(id, remoteControllerInputDto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController (@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }
}
