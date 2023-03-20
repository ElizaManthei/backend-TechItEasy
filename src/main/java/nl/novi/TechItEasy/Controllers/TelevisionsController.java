package nl.novi.TechItEasy.Controllers;

import jakarta.validation.Valid;
import nl.novi.TechItEasy.Services.TelevisionService;
import nl.novi.TechItEasy.dtos.IdInputDto;
import nl.novi.TechItEasy.dtos.TelevisionDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("tvs")
public class TelevisionsController {
    private final TelevisionService televisionService;
    public TelevisionsController (TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {
        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;
        return ResponseEntity.created(null).body(televisionService.saveTelevision(televisionInputDto));
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getTvs(){
        return ResponseEntity.ok(televisionService.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTvById( @PathVariable Long id ) {
        return ResponseEntity.ok(televisionService.getTelevisionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTv(@PathVariable Long id, @RequestBody TelevisionInputDto televisionInputDto) {
        return ResponseEntity.ok().body(televisionService.updateTelevision(id, televisionInputDto));
    }

    @PutMapping("/{id}/remote-controller")
    public ResponseEntity<Object> assignRemoteControllerToTelevision (@PathVariable Long id, @RequestBody IdInputDto remoteControllerId) {
        return  ResponseEntity.ok(televisionService.assignRemoteControllerToTelevision(id, remoteControllerId));
    }

    @PutMapping("/{id}/ci-module")
    public ResponseEntity<Object> assignCIModule(@PathVariable Long id, @RequestBody IdInputDto cIModuleId) {
        return ResponseEntity.ok( televisionService.assignCIModule(id, cIModuleId));
    }

    @PutMapping("/{id}/wall-bracket")
    public ResponseEntity<Object>   assignWallBracket(@PathVariable Long id, @RequestBody IdInputDto   wallBracketId) {
        return ResponseEntity.ok( televisionService.assignWallBracket(id, wallBracketId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTvById (@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
