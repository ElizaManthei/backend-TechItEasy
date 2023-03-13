package nl.novi.TechItEasy.Controllers;

import jakarta.validation.Valid;
import nl.novi.TechItEasy.Services.TelevisionService;
import nl.novi.TechItEasy.dtos.TelevisionDto;
import nl.novi.TechItEasy.dtos.TelevisionInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("tvs")
public class TelevisionsController {
    private final TelevisionService service;
    public TelevisionsController (TelevisionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {
        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;
        Long id = service.saveTelevision(televisionInputDto);
        televisionInputDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(televisionInputDto);
    }

    @GetMapping
    public ResponseEntity<Object> getTvs(){
        return ResponseEntity.ok(service.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTvById(@PathVariable Long id) {
        TelevisionDto televisionDto = service.getTelevision(id);
        televisionDto.id = id;
        return ResponseEntity.ok(televisionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {
        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;
        service.updateTelevision(id, televisionInputDto);
        return ResponseEntity.ok(televisionInputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTvById (@PathVariable Long id) {
        service.deleteTelevision(id);
        return ResponseEntity.noContent().build();
//        return ResponseEntity.ok().body("Deleted");
    }

    private ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
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
