package dsmi.folkracelive.controllers;

import dsmi.folkracelive.dto.heatInfo.HeatInformationThreeDTO;
import dsmi.folkracelive.services.HeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")

public class HeatController {

    private final HeatService heatService;

    @Autowired
    public HeatController(HeatService heatService) {
        this.heatService = heatService;
    }

    @PostMapping("heatService/randomizeNewHeatRounds")
    public List<HeatInformationThreeDTO> randomizeNewHeatRounds(@RequestBody Map<String, Object> body){
       heatService.randomizeNewHeatRounds(body);

        return null;

    }
}
