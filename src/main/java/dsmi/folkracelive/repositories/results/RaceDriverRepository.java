package dsmi.folkracelive.repositories.results;

import dsmi.folkracelive.entities.RaceDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceDriverRepository extends JpaRepository<RaceDriver, Long> {
}
