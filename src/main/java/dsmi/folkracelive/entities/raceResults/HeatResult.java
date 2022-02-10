package dsmi.folkracelive.entities.raceResults;

import javax.persistence.Column;

public class HeatResult extends  DriverResult{

    private int round;
    @Column(name = "heat_number")

    private int heatNumber;
    private int points;

}
