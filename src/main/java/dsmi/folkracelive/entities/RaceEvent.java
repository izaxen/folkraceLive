package dsmi.folkracelive.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "race_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RaceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="event_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "event_name",
            nullable = false
    )
    private String eventName;
    @Column(
            name = "event_date",
            nullable = false
    )
    private LocalDate eventDate;

}
