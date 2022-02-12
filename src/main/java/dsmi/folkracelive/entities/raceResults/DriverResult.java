package dsmi.folkracelive.entities.raceResults;

import javax.persistence.*;

@MappedSuperclass
public class DriverResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "driver_result_id")
    private Long driverResultId;

    @Column(name="start_number")
    private int startNumber;

    private String placement;

    //DriverclassID


}
