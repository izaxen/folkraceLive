package dsmi.folkracelive.dto.models;

import dsmi.folkracelive.entities.RaceEvent;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserNoPwDTO {
    private Long Id;
    private String clubName, clubImage, email;
    private List<RaceEvent> raceEventList;
}
