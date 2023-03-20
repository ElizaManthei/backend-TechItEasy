package nl.novi.TechItEasy.Repositories;

import nl.novi.TechItEasy.Models.RemoteController;
import nl.novi.TechItEasy.dtos.IdInputDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    Optional<RemoteController> findById(IdInputDto remoteControllerId);
}
