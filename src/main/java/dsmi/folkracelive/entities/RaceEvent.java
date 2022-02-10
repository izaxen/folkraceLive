package dsmi.folkracelive.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "race-event")
public class RaceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "event-name",
            nullable = false
    )
    private String eventName;
    @Column(
            name = "event-date",
            nullable = false
    )
    private LocalDate eventDate;

}
