package dsmi.folkracelive.services;

import dsmi.folkracelive.entities.HeatRounds;
import dsmi.folkracelive.entities.RaceDriver;
import dsmi.folkracelive.entities.RaceEvent;
import dsmi.folkracelive.repositories.HeatRoundsRepository;
import dsmi.folkracelive.repositories.RaceDriverRepository;
import dsmi.folkracelive.repositories.RaceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeatService {
    @Autowired
    private HeatRoundsRepository heatRoundsRepository;

    @Autowired
    private RaceDriverRepository raceDriverRepository;

    @Autowired
    private RaceEventRepository raceEventRepository;

    private final double CONTESTANT = 6.0;
    private final int CONTESTANTHEATCOUNT = 5;


    public void randomizeNewHeatRounds(Map<String, Object> body) {
        HashMap<String, Integer> id = (HashMap<String, Integer>) body.get("raceEvent");
        Long eventId = Long.valueOf(id.get("id"));
        int round = (int) body.get("round");

        String raceClass = (String) body.get("class");

        heatRoundsRepository.deleteHeatRoundsByRound(eventId, round,raceClass);

        List<RaceDriver> driv = raceDriverRepository.getRaceDriversByEventClassCancellation(eventId, raceClass);
        List<Integer> drawnRound;
        switch (round) {
            case 1 -> drawnRound = drawRoundOne(driv.size());
            case 2 -> drawnRound = drawRoundTwo(driv.size());
            default -> drawnRound = drawRoundThreeOrMore(driv.size());
        }

        if(driv.size() != drawnRound.size()) return;
        saveRoundToDb(eventId, driv, round, raceClass, drawnRound);
        List<HeatRounds> nn = heatRoundsRepository.getHeatRoundsByRound(eventId, round,raceClass);
        System.out.println(nn);
    }

    private List<Integer> drawRoundOne(int driverListSize) {
        List<Integer> newRoundList = createHeatList(driverListSize);
        Collections.sort(newRoundList);
        return newRoundList;
    }

    private List<Integer> drawRoundTwo(int driverListSize) {
        return createRoundTwo(driverListSize);
    }

    private List<Integer> drawRoundThreeOrMore(int driverListSize) {
        List<Integer> newRoundList = createHeatList(driverListSize);
        Collections.shuffle(newRoundList);
        return newRoundList;
    }


    private List<Integer> createRoundTwo(int driverListSize) {

        int heats = (int) Math.ceil(driverListSize / CONTESTANT);
        List<Integer> newRoundList = new ArrayList<>();

        for (int i = 0; i < CONTESTANTHEATCOUNT; i++) {
            int[] sortedList = sortedListRoundTwo(heats);
            for (int number : sortedList) {
                newRoundList.add(number);
            }
        }

        int[] sortedListRest = sortedListRoundTwo(driverListSize - newRoundList.size());
        for (int number : sortedListRest) {
            newRoundList.add(number);
        }
        return newRoundList;
    }

    private int[] sortedListRoundTwo(int heats) {

        int[] arr = new int[heats];

        for (int i = 0; i < heats; i++) {
            for (int j = 0; j < CONTESTANTHEATCOUNT; j++) {
                arr[i] = i + 1;
            }
        }
        int l = 0, r = heats - 1;
        int k = 0;

        while (l < r) {
            while (arr[l] % 2 != 0) {
                l++;
                k++;
            }

            while (arr[r] % 2 == 0 && l < r)
                r--;
            if (l < r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        Arrays.sort(arr, 0, k);
        Arrays.sort(arr, k, heats);
        return arr;
    }

    private void saveRoundToDb(Long eventId, List<RaceDriver> driv, int round, String raceClass, List<Integer> newRoundList) {
        RaceEvent raceEvent = raceEventRepository.getById(eventId);
        for (int i = 0; i < newRoundList.size() ; i++) {
           HeatRounds driver = HeatRounds.builder()
                    .heatNumber(newRoundList.get(i))
                    .round(round)
                    .raceClass(raceClass)
                    .startNumber(driv.get(i))
                    .raceEvent(raceEvent)
                    .build();
            heatRoundsRepository.save(driver);
        }
    }

    private List<Integer> createHeatList(int driverListSize) {

        int heats = (int) Math.ceil(driverListSize / CONTESTANT);
        List<Integer> newRoundList = new ArrayList<>();
        for (int i = 0; i < heats; i++) {
            for (int j = 0; j < CONTESTANTHEATCOUNT; j++) {
                newRoundList.add(i + 1);
            }
        }
        int remainingHeat = driverListSize - newRoundList.size();
        for (int i = 0; i < remainingHeat; i++) {
            newRoundList.add(i + 1);
        }
        return newRoundList;
    }
}
