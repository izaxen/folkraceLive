package dsmi.folkracelive.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RaceDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="driver_id",
            updatable = false
    )
    private Long id;

    private int startNumber;

    private String name;
    @Column(
            name = "sur_name",
            nullable = false
    )
    private String surName;

    private String dateOfBirth;

    private String club;
    @Column(
            name = "car_model",
            nullable = false
    )
    private String carModel;
    private String notifier;
    @Column(
            name ="race_class",
            nullable = false
    )
    private String raceClass;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    public LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private RaceEvent raceEvent;
}
