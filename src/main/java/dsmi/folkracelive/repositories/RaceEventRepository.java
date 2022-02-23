package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.RaceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEventRepository extends JpaRepository<RaceEvent, Long> {


}
