package nl.novi.TechItEasy.Controllers;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.Tv;
import nl.novi.TechItEasy.Repositories.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("tvs")
public class TelevisionsController {

    @Autowired
    TvRepository tvRepository;
    @GetMapping
    public ResponseEntity<Iterable<Tv>> getTvs() {
        return ResponseEntity.ok(tvRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tv>> getTvById(@PathVariable Long id) {
        if (!tvRepository.existsById(id)) {
            throw new RecordNotFoundException(("ID cannot be found"));
        }
        return ResponseEntity.ok().body(tvRepository.findById(id));
    }
    @PostMapping
    public ResponseEntity<Tv> createTv (@RequestBody Tv tv) {
        tvRepository.save(tv);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + tv.getId()).toUriString());
        return ResponseEntity.created(uri).body(tv);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable Long id, @RequestBody Tv tv) {
        if (!tvRepository.existsById(id)) {
            throw new RecordNotFoundException("ID cannot be found");
        }
    Tv newTv = tvRepository.getReferenceById(id);
    newTv.setName(tv.getName());
    newTv.setAmbiLight(tv.getAmbiLight());
    newTv.setBrand(tv.getBrand());
    newTv.setHdr(tv.getHdr());
    newTv.setBluetooth(tv.getBluetooth());
    newTv.setAvailableSize(tv.getAvailableSize());
    newTv.setOriginalStock(tv.getOriginalStock());
    newTv.setPrice(tv.getPrice());
    newTv.setRefreshRate(tv.getRefreshRate());
    newTv.setScreenQuality(tv.getScreenQuality());
    newTv.setScreenType(tv.getScreenType());
    newTv.setSmartTv(tv.getSmartTv());
    newTv.setSold(tv.getSold());
    newTv.setType(tv.getType());
    newTv.setVoiceControl(tv.getVoiceControl());
    newTv.setWifi(tv.getWifi());
        tvRepository.save(newTv);
        return ResponseEntity.ok().body("Updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTvById (@PathVariable Long id) {
        if (!tvRepository.existsById(id)) {
            throw new RecordNotFoundException("ID cannot be found");
        }
       tvRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
