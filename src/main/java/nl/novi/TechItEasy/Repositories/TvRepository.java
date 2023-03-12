package nl.novi.TechItEasy.Repositories;

import nl.novi.TechItEasy.Models.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface TvRepository extends JpaRepository<Tv, Long> {
}
