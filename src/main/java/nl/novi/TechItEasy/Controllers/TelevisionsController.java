package nl.novi.TechItEasy.Controllers;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Exceptions.RecordTooLongException;
import nl.novi.TechItEasy.Model.Tv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tvs")
public class TelevisionsController {
private List<Tv> tvsDataBase = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<Object> getTvs () {
        return new ResponseEntity<>(tvsDataBase, HttpStatus.OK);
    }

@GetMapping("/{id}")
public ResponseEntity<Object> getTvById(@PathVariable int id) {
//        if (id < 0 || id >= tvs.size()){
//            return new ResponseEntity<>("Not exist", HttpStatus.NOT_FOUND);
//        }
    if (id < 0 || id >= tvsDataBase.size()) { // determine if id exists
        throw new RecordNotFoundException("ID cannot be found");
    }
        return ResponseEntity.ok().body(tvsDataBase.get(id));
}

    @PostMapping("")
    public ResponseEntity<Tv> addTv (@RequestBody Tv ntv) {
    if (ntv.name.length() > 20) {
        throw new RecordTooLongException("The name is too long");
    }
        tvsDataBase.add(ntv);
//    return new ResponseEntity<>( ntv, HttpStatus.CREATED);
      return   ResponseEntity.created(null).body(ntv);
}

@PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable int id, @RequestBody Tv tv) {
        if (id < 0 || id >= tvsDataBase.size()) {
            throw new RecordNotFoundException("ID cannot be found");
        }
        tvsDataBase.set(id, tv);
        return ResponseEntity.ok( tvsDataBase.get(id));
}
@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTvById (@PathVariable int id) {
    if (id < 0 || id >= tvsDataBase.size()) {
        throw new RecordNotFoundException("ID cannot be found");
    }
    tvsDataBase.remove(id);

    return ResponseEntity.noContent().build();
}
}
