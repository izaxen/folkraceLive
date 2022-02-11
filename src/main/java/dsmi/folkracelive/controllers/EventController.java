package dsmi.folkracelive.controllers;


import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/raceEvent/createNewEvent")
    public RaceEvent createNewEvent(@RequestBody RaceEvent raceEvent){
        return eventService.saveNewEventToDB(raceEvent);
    }
}
