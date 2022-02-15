package dsmi.folkracelive.repositories.results;

import dsmi.folkracelive.entities.raceResults.HeatResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatResultRepository extends JpaRepository<HeatResult, Long> {
}
