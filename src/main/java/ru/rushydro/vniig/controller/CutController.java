package ru.rushydro.vniig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rushydro.vniig.entry.InsisionSensors;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.service.InsisionSernsorsService;
import ru.rushydro.vniig.service.PassportParamSysService;

import java.util.List;

/**
 * Created by nikolay on 18.10.15.
 */
@Controller
@RequestMapping(path = "/cut/{id}")
public class CutController {
    @Autowired
    PassportParamSysService sensorService;

    @Autowired
    InsisionSernsorsService insisionSernsorsService;

    @RequestMapping
    public String indexPage(Model model, @PathVariable(value = "id") String cutIdStr) {
        Integer cutId = 0;
        if (cutIdStr != null && !cutIdStr.isEmpty()) {
            try {
                cutId = Integer.parseInt(cutIdStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        List<String> roots = sensorService.getRootNodesByInsision(cutId);
        List<PassportParamSys> passportParamSysList = sensorService.getSensorByTypeAndInsision(1, cutId);

        List<InsisionSensors> insisionSernsorsList = insisionSernsorsService.getSensorsByIns(cutId);

        for (InsisionSensors insisionSensors : insisionSernsorsList) {
            for (PassportParamSys sensor : passportParamSysList) {
                if (insisionSensors.getPassportParamSys().getIdSensors().equals(sensor.getIdSensors())) {
                    sensor.setxValue(insisionSensors.getxValue());
                    sensor.setyValue(insisionSensors.getyValue());
                }
            }
        }

        model.addAttribute("roots", roots);
        model.addAttribute("cutId", cutId);
        model.addAttribute("sensors", passportParamSysList);
        return "cut";
    }
}
