package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.RaceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<RaceEvent, Long> {


}
