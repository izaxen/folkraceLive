package dsmi.folkracelive.DTO.HeatInformation;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HeatInformationThreeDTO {
    private String raceClass;
    private int startNumber;
    private String name;
    private String surName;
    private String club;
    private String notifier;
    private int round;
    private int heatNumber;
    private String pointsRoundOne;
    private String pointsRoundTwo;
    private String pointsRoundThree;

}

