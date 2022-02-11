package dsmi.folkracelive.services;

import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
   @Autowired
    private EventRepository eventRepository;

   public RaceEvent saveNewEventToDB(RaceEvent event){
       return eventRepository.save(event);

   }
}
