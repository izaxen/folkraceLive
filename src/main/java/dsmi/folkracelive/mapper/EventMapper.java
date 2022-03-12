package dsmi.folkracelive.mapper;

import dsmi.folkracelive.dto.models.CreateNewEventDTO;
import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.entities.User;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public RaceEvent createNewEvent(User user, CreateNewEventDTO event){

    return RaceEvent.builder()
            .eventName(event.getEventName())
            .eventDate(event.getEventDate())
            .user(user)
            .build();
    }
}
