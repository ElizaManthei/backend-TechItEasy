package nl.novi.TechItEasy.Controllers;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.Tv;
import nl.novi.TechItEasy.Repositories.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tvs")
public class TelevisionsController {
//private List<Tv> tvsDataBase = new ArrayList<>();

//    @GetMapping("")
//    public ResponseEntity<Object> getTvs () {
//        return new ResponseEntity<>(tvsDataBase, HttpStatus.OK);
//    }


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
//    @GetMapping("/{id}")
//public ResponseEntity<Object> getTvById(@PathVariable int id) {
//    if (id < 0 || id >= tvsDataBase.size()) { // determine if id exists
//        throw new RecordNotFoundException("ID cannot be found");
//    }
//        return ResponseEntity.ok().body(tvsDataBase.get(id));
//}

//    @PostMapping("")
//    public ResponseEntity<Tv> addTv (@RequestBody Tv ntv) {
//    if (ntv.name.length() > 20) {
//        throw new RecordTooLongException("The name is too long");
//    }
//        tvsDataBase.add(ntv);
////    return new ResponseEntity<>( ntv, HttpStatus.CREATED);
//      return   ResponseEntity.created(null).body(ntv);
//}

//@PutMapping("/{id}")
//    public ResponseEntity<Object> updateTv(@PathVariable int id, @RequestBody Tv tv) {
//        if (id < 0 || id >= tvsDataBase.size()) {
//            throw new RecordNotFoundException("ID cannot be found");
//        }
//        tvsDataBase.set(id, tv);
//        return ResponseEntity.ok( tvsDataBase.get(id));
//}
//@DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTvById (@PathVariable int id) {
//    if (id < 0 || id >= tvsDataBase.size()) {
//        throw new RecordNotFoundException("ID cannot be found");
//    }
//    tvsDataBase.remove(id);
//
//    return ResponseEntity.noContent().build();
//}
}
