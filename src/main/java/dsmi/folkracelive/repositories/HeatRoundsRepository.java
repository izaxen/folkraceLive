package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.HeatRounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatRoundsRepository extends JpaRepository <HeatRounds, Long>{


}
