package nl.novi.TechItEasy.Controllers;

import jakarta.validation.Valid;
import nl.novi.TechItEasy.Services.WallBracketService;
import nl.novi.TechItEasy.dtos.WallBracketDto;
import nl.novi.TechItEasy.dtos.WallBracketInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static nl.novi.TechItEasy.Controllers.TelevisionsController.getObjectResponseEntity;

@RestController
@RequestMapping("wallbracket")
public class WallBracketController {
    private final WallBracketService wallBracketService;

    public WallBracketController (WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }
    @PostMapping
    public ResponseEntity<Object> createWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto, BindingResult br){
        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;
        return ResponseEntity.created(null).body(wallBracketService.saveWallBracket(wallBracketInputDto));
    }
    @GetMapping
    public ResponseEntity<Object> getWallBrackets(){
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }
    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable Long id){
       return ResponseEntity.ok(wallBracketService.getWallBracketById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@RequestBody WallBracketInputDto wallBracketInputDto, @PathVariable Long id){
        return ResponseEntity.ok().body(wallBracketService.updateWallBracket(id, wallBracketInputDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket (@PathVariable Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }
}
