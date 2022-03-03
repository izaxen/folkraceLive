package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.HeatRounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HeatRoundsRepository extends JpaRepository <HeatRounds, Long>{



   @Query(value = "SELECT * FROM heat_rounds hr WHERE hr.event_id =?1 AND hr.round =?2 AND hr.race_class =?3 ",
   nativeQuery = true)
    List<HeatRounds> getHeatRoundsByRound(Long id, int round, String raceClass);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM heat_rounds hr WHERE hr.event_id =?1 AND hr.round =?2 AND hr.race_class =?3",
            nativeQuery = true)
    void deleteHeatRoundsByRound(Long id, int round, String raceClass);

 @Query(value = "SELECT * FROM heat_rounds hr, race_drivers WHERE hr.event_id =?1 AND hr.round =?2 AND hr.race_class =?3",
         nativeQuery = true)
 List<Object> getHeatRoundsByRound1(Long id, int round, String raceClass);




}
