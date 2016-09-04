package ru.rushydro.vniig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.rushydro.vniig.dao.PassportParamSysDAO;
import ru.rushydro.vniig.entry.SignSys;
import ru.rushydro.vniig.model.SensorUpdateData;
import ru.rushydro.vniig.service.MeasParamSysService;
import ru.rushydro.vniig.service.SignSysService;
import ru.rushydro.vniig.storage.service.MeasParamSysStorageService;
import ru.rushydro.vniig.util.data.UpdateTimeUtil;

import java.beans.PropertyEditorSupport;

/**
 * Created by nikolay on 11.10.15.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    UpdateTimeUtil updateTimeUtil;

    @Autowired
    PassportParamSysDAO passportParamSysDAO;

    @Autowired
    SignSysService signSysService;

    @Autowired
    MeasParamSysService measParamSysService;

    @Autowired
    MeasParamSysStorageService measParamSysStorageService;

    @RequestMapping(value = "/updateSensor/{insId}", method = RequestMethod.GET)
    public @ResponseBody SensorUpdateData getSensorUpdateData(@PathVariable("insId") Integer insId) {
        SensorUpdateData sensorUpdateData = new SensorUpdateData();
        sensorUpdateData.setUpdate(true);
        if (sensorUpdateData.getUpdate()) {
            if (insId == 0) {
                sensorUpdateData.setSensors(passportParamSysDAO.getSensorByType(1));
            } else {
                sensorUpdateData.setSensors(passportParamSysDAO.getSensorByTypeAndInsision(1, insId));
            }

        }
        return sensorUpdateData;
    }

    @RequestMapping(value = "/kvintSensor", method = RequestMethod.POST)
    public @ResponseBody Boolean kventSensor(@RequestParam("id") Integer id) {
        return signSysService.kventSensor(id);
    }

    @RequestMapping(value = "/onSensor/{id}", method = RequestMethod.GET)
    public @ResponseBody Boolean onSensor(@PathVariable("id") Integer id) {
        measParamSysService.onSensor(id);
        measParamSysStorageService.onSensor(id);
        return  false;
    }

    @RequestMapping(value = "/offSensor/{id}", method = RequestMethod.GET)
    public @ResponseBody Boolean ofSensor(@PathVariable("id") Integer id) {
        measParamSysService.offSensor(id);
        measParamSysStorageService.offSensor(id);
        return true;
    }

//    @InitBinder
//    public void binder(WebDataBinder binder) {
//        binder.registerCustomEditor(SensorUpdateData.class, new PropertyEditorSupport());
//    }
}
