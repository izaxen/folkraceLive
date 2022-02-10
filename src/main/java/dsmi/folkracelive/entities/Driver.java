package dsmi.folkracelive.entities;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="id",
            updatable = false
    )
    private Long id;
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
    private LocalDateTime createdAt;
}
