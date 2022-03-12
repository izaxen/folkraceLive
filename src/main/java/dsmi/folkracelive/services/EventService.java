package dsmi.folkracelive.services;

import dsmi.folkracelive.dto.models.CreateNewEventDTO;
import dsmi.folkracelive.entities.RaceDriver;
import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.entities.driverClasses.*;
import dsmi.folkracelive.mapper.EventMapper;
import dsmi.folkracelive.repositories.RaceDriverRepository;
import dsmi.folkracelive.repositories.RaceEventRepository;
import dsmi.folkracelive.repositories.driverClassRepository.JuniorClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.LadyClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.SeniorClassRepository;
import dsmi.folkracelive.repositories.driverClassRepository.VeteranClassRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final RaceEventRepository raceEventRepository;
    private final JuniorClassRepository juniorClassRepository;
    private final SeniorClassRepository seniorClassRepository;
    private final LadyClassRepository ladyClassRepository;
    private final VeteranClassRepsitory veteranClassRepsitory;
    private final RaceDriverRepository raceDriverRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(RaceEventRepository raceEventRepository, JuniorClassRepository juniorClassRepository
            , SeniorClassRepository seniorClassRepository, LadyClassRepository ladyClassRepository
            , VeteranClassRepsitory veteranClassRepsitory, RaceDriverRepository raceDriverRepository
            , EventMapper eventMapper) {
        this.raceEventRepository = raceEventRepository;
        this.juniorClassRepository = juniorClassRepository;
        this.seniorClassRepository = seniorClassRepository;
        this.ladyClassRepository = ladyClassRepository;
        this.veteranClassRepsitory = veteranClassRepsitory;
        this.raceDriverRepository = raceDriverRepository;
        this.eventMapper = eventMapper;
    }

    public RaceEvent saveNewEventToDB(CustomUserDetails user, CreateNewEventDTO event) {
        return raceEventRepository.save(eventMapper.createNewEvent(user.getUser(), event));
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
        raceDriverRepository.saveAll(raceDriverList);
    }
}
