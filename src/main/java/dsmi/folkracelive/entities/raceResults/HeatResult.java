package dsmi.folkracelive.entities.raceResults;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class HeatResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "heat_id")
    private Long heatId;

    private int heatNumber;
    private int round;

}
