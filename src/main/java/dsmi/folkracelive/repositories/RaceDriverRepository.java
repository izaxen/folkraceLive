package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.RaceDriver;
import dsmi.folkracelive.entities.RaceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceDriverRepository extends JpaRepository<RaceDriver, Long> {

    @Query(value = "SELECT * FROM race_drivers r WHERE r.event_id =?1 AND r.race_class = ?2 AND r.retired = false",
    nativeQuery = true)
    List<RaceDriver> getRaceDriversByEventClassCancellation(Long id, String raceClass);
}
