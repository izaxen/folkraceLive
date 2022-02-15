package dsmi.folkracelive.services;

import dsmi.folkracelive.entities.RaceDriver;
import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.entities.driverClasses.*;
import dsmi.folkracelive.repositories.EventRepository;
import dsmi.folkracelive.repositories.driverClassRepository.JuniorClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.LadyClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.SeniorClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.VeteranClassRepsitory;
import dsmi.folkracelive.repositories.results.RaceDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private JuniorClassRepository juniorClassRepository;
    @Autowired
    private SeniorClassRepository seniorClassRepository;
    @Autowired
    private LadyClassRepository ladyClassRepository;
    @Autowired
    private VeteranClassRepsitory veteranClassRepsitory;
    @Autowired
    private RaceDriverRepository raceDriverRepository;


    public RaceEvent saveNewEventToDB(RaceEvent event) {
        return eventRepository.save(event);
    }

    public DriverClasses createNewClassJunior(JuniorClass juniorClass) {
        return juniorClassRepository.save(juniorClass);
    }

    public DriverClasses createNewClassSenior(SeniorClass seniorClass) {
        return seniorClassRepository.save(seniorClass);
    }

    public DriverClasses createNewClassLady(LadyClass ladyClass) {
        return ladyClassRepository.save(ladyClass);
    }

    public DriverClasses createNewClassVeteran(VeteranClass veteranClass) {
        return veteranClassRepsitory.save(veteranClass);
    }

    public void addDriversToEvent(List<RaceDriver> raceDriverList) {
        for (RaceDriver raceDriver : raceDriverList
        ) {
            raceDriverRepository.save(raceDriver);
        }
    }
}
