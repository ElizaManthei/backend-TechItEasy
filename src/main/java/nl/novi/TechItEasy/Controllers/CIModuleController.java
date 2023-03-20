package nl.novi.TechItEasy.Controllers;

import jakarta.validation.Valid;
import nl.novi.TechItEasy.Services.CIModuleService;
import nl.novi.TechItEasy.dtos.CIModuleDto;
import nl.novi.TechItEasy.dtos.CIModuleInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cim")
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService){
        this.ciModuleService = ciModuleService;
    }

    @PostMapping
    public ResponseEntity<Object> createCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto, BindingResult br ){
        ResponseEntity<Object> sb = TelevisionsController.getObjectResponseEntity(br);
        if (sb != null) return sb;

        return ResponseEntity.created(null).body(ciModuleService.saveCIModule(ciModuleInputDto));
    }

    @GetMapping
    public ResponseEntity<Object> getCIModules(){
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(ciModuleService.getCIModule(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@RequestBody CIModuleInputDto ciModuleInputDto, @PathVariable Long id){
        return ResponseEntity.ok((ciModuleService.updateCIModule(id, ciModuleInputDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule (@PathVariable Long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }
}
