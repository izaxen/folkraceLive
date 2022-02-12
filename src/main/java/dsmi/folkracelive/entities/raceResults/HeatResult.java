package dsmi.folkracelive.entities.raceResults;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HeatResult extends  DriverResult{

    private int round;
    @Column(name = "heat_number")

    private int heatNumber;
    private int points;

}
