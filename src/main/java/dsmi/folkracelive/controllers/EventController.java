package dsmi.folkracelive.controllers;


import dsmi.folkracelive.dto.models.CreateNewEventDTO;
import dsmi.folkracelive.entities.RaceDriver;
import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.entities.driverClasses.*;
import dsmi.folkracelive.services.CustomUserDetails;
import dsmi.folkracelive.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/raceEvent/createNewEvent")
    public RaceEvent createNewEvent(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateNewEventDTO raceEvent) {
        return eventService.saveNewEventToDB(userDetails, raceEvent);
    }

    @PostMapping("/raceEvent/createNewClass/junior")
    public DriverClasses createNewClassJunior(@RequestBody JuniorClass juniorClass) {
        return eventService.createNewClassJunior(juniorClass);
    }

    @PostMapping("/raceEvent/createNewClass/senior")
    public DriverClasses createNewClassSenior(@RequestBody SeniorClass seniorClass) {
        return eventService.createNewClassSenior(seniorClass);
    }

    @PostMapping("/raceEvent/createNewClass/lady")
    public DriverClasses createNewClassLady(@RequestBody LadyClass ladyClass) {
        return eventService.createNewClassLady(ladyClass);
    }

    @PostMapping("/raceEvent/createNewClass/veteran")
    public DriverClasses createNewClassVeteran(@RequestBody VeteranClass veteranClass) {
        return eventService.createNewClassVeteran(veteranClass);
    }

    @PostMapping("/raceEvent/driversList")
    public void createDrivers(@RequestBody List<RaceDriver> raceDriverList) {
        eventService.addDriversToEvent(raceDriverList);
    }


}
