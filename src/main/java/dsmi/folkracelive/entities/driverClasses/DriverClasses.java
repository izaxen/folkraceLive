package dsmi.folkracelive.entities.driverClasses;

import dsmi.folkracelive.entities.RaceEvent;

import javax.persistence.*;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "event_id" )
    private RaceEvent raceEvent;

}
