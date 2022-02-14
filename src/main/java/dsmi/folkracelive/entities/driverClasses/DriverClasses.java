package dsmi.folkracelive.entities.driverClasses;

import dsmi.folkracelive.entities.Driver;
import dsmi.folkracelive.entities.RaceEvent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
@Getter
@Setter

 public class DriverClasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_class_id", updatable = false)
    protected Long id;

    @Column(
            name = "amount_of_rounds",
            nullable = false
    )
    protected int amountOfRounds;

    @Column(
            name = "runner_up",
            nullable = false
    )
    protected boolean runnerUp;

    @Column(
            name = "amount_of_finals",
            nullable = false
    )
    protected int amountOfFinals;

    @Transient
    protected List<Driver> drivers;


    @OneToOne
    @JoinColumn(name = "event_id",
    referencedColumnName = "event_id")
    private RaceEvent raceEvent;

}
