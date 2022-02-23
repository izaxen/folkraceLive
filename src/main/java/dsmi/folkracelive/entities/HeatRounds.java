package dsmi.folkracelive.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="heat_rounds")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeatRounds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heat_round_id",
            nullable = false

    )
    private Long heatRoundId;

    @Column(name = "heat_number")
    private int heatNumber;

    private int round;

    @Column(name = "race_class")
    private String raceClass;

    @Column(name = "start_number")
    private int startNumber;

    @OneToOne
    @JoinColumn(name = "event_id",
    referencedColumnName = "event_id")
    private RaceEvent raceEvent;

}
