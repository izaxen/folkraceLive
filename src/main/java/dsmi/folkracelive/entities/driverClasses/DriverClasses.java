package dsmi.folkracelive.entities.driverClasses;

import dsmi.folkracelive.entities.Driver;
import dsmi.folkracelive.entities.RaceEvent;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
abstract public class DriverClasses {

    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(
            name = "amount_of_rounds",
            nullable = false
    )
    private int amountOfRounds;

    @Column(
            name = "runner_up",
            nullable = false
    )
    private boolean runnerUp;

    @Column(
            name = "amount_of_finals",
            nullable = false
    )
    private int amountOfFinals;

    @Transient
    private List<Driver> drivers;


    @OneToOne
    @MapsId
    @JoinColumn(name = "event_id" )
    private RaceEvent raceEvent;

}
