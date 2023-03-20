package nl.novi.TechItEasy.Services;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Models.WallBracket;
import nl.novi.TechItEasy.Repositories.WallBracketRepository;
import nl.novi.TechItEasy.dtos.WallBracketDto;
import nl.novi.TechItEasy.dtos.WallBracketInputDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService (WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public WallBracketDto saveWallBracket(WallBracketInputDto wallBracketInputDto) {
       WallBracketDto wallBracketDto = new WallBracketDto();
       WallBracket newWallBracket = new WallBracket();
       newWallBracket = wallBracketInputDto.toWallBracket(newWallBracket);
       wallBracketRepository.save(newWallBracket);
       return wallBracketDto.fromWallBracket(newWallBracket);
    }

    public List<WallBracketDto> getAllWallBrackets() {
        WallBracketDto wallBracketDto = new WallBracketDto();
       return wallBracketDto.fromWallBracketList(wallBracketRepository.findAll());
    }

    public WallBracketDto getWallBracketById (Long id) {
        WallBracket wallBracketFound = wallBracketRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Wall bracket not found"));
        WallBracketDto wallBracketDto = new WallBracketDto();
        return wallBracketDto.fromWallBracket(wallBracketFound);
    }
    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracketFound = wallBracketRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cannot find Wall Bracket"));
        WallBracket updatedWallBracket = wallBracketInputDto.toWallBracket(wallBracketFound);
        wallBracketRepository.save(updatedWallBracket);
        WallBracketDto wallBracketDto = new WallBracketDto();
        return wallBracketDto.fromWallBracket(updatedWallBracket);
    }
    public void deleteWallBracket(Long id) {
        if (!wallBracketRepository.existsById(id))
            throw new RecordNotFoundException("Cannot find wall bracket");
        wallBracketRepository.deleteById(id);
    }
}
