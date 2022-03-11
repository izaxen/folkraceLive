package dsmi.folkracelive.dto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateNewEventDTO {

    private Long id;
    private String eventName;
    private String eventDate;

}
