package ru.rushydro.vniig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rushydro.vniig.entry.*;
import ru.rushydro.vniig.service.*;
import ru.rushydro.vniig.storage.entry.*;
import ru.rushydro.vniig.storage.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikolay on 07.02.16.
 */
@RequestMapping("/virtual/sensors")
@Controller
public class VirtualSensorsController {

    @Autowired
    PassportParamSysService passportParamSysService;

    @Autowired
    PassportParamSysStorageService passportParamSysStorageService;

    @Autowired
    UstavkaParamSysService ustavkaParamSysService;

    @Autowired
    UstavkaParamSysStorageService ustavkaParamSysStorageService;

    @Autowired
    MeasParamSysService measParamSysService;

    @Autowired
    MeasParamSysStorageService measParamSysStorageService;

    @Autowired
    TypeSignalTableService typeSignalTableService;

    @Autowired
    TypeSignalTableStorageService typeSignalTableStorageService;

    @Autowired
    SignSysService signSysService;

    @Autowired
    SignSysStorageService signSysStorageService;

    @RequestMapping()
    public String showVirualSensors (Model model,
                                     @RequestParam(required = false) String message,
                                     @RequestParam(required = false) String error) {

        model.addAttribute("sensors", passportParamSysService.getSensorByTypeUpper(4));
        model.addAttribute("message", message);
        model.addAttribute("error", error);

        return "virtualSensors";
    }

    @RequestMapping("/add")
    public String addSensor (Model model) {

        model.addAttribute("sensor", new PassportParamSys());
        model.addAttribute("types", passportParamSysService.getRootComboItems());
        model.addAttribute("sensorTypes", ustavkaParamSysService.getComboItems());

        return "sensor";
    }

    @RequestMapping("/type/add")
    public String addSensorType (Model model) {

        model.addAttribute("type", new UstavkaParamSys());

        return "type";
    }

    @RequestMapping("/value/add/{id}")
    public String addSensorValue (Model model, @PathVariable Integer id) {

        PassportParamSys passportParamSys = passportParamSysService.getById(id);
        if (passportParamSys == null || passportParamSys.getMeasParamTypeSig().getIdUstavka() < 4) {
            return "redirect:/virtual/sensors";
        }

        model.addAttribute("sensor", passportParamSys);

        return "addValue";
    }

    @RequestMapping(value = "/value/add", method = RequestMethod.POST)
    public String addSensorValue (ModelMap modelMap, @RequestParam Integer id,
                                  @RequestParam(required = false) Float value, @RequestParam(required = false) String date,
                                  @RequestParam(required = false) String time, RedirectAttributes redirectAttributes) {



        PassportParamSys passportParamSys = passportParamSysService.getById(id);

        PassportParamSysStorage passportParamSysStorage = passportParamSysStorageService.getById(id);

        if (passportParamSys != null && passportParamSysStorage != null) {
            //Сохранение в оперативную базу



            MeasParamSys measParamSys = measParamSysService.getById(id);
            try {
                measParamSys.setDateMeas(new SimpleDateFormat("dd.MM.yy").parse(date));
                measParamSys.setTimeMeas(new SimpleDateFormat("hh:mm:ss").parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            measParamSysService.save(measParamSys);

            SignSys signSys = signSysService.getById(id);
            try {
                signSys.setTimeSign(new SimpleDateFormat("dd.MM.yy").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int type = signSys.getSortSign().getIdSignal();

            if (type == 2) {
                if (value <= passportParamSys.getCriter_release()) {
                    signSys.setSortSign(typeSignalTableService.getById(1));
                }
            } else if (type == 1) {
                if (value > passportParamSys.getCriterion()) {
                    signSys.setSortSign(typeSignalTableService.getById(2));
                }
            }

            signSysService.save(signSys);

            type = signSys.getSortSign().getIdSignal();

            //Сохранение в хранилище
            MeasParamSysStorage measParamSysStorage = new MeasParamSysStorage();
            measParamSysStorage.setPassportParamSys(passportParamSysStorage);
            measParamSysStorage.setValueMeas(value);
            try {
                measParamSysStorage.setDateMeas(new SimpleDateFormat("dd.MM.yy").parse(date));
                measParamSysStorage.setTimeMeas(new SimpleDateFormat("hh:mm:ss").parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            measParamSysStorageService.save(measParamSysStorage);

            SignSysStorage signSysStorage = new SignSysStorage();
            try {
                signSysStorage.setTimeSign(new SimpleDateFormat("dd.MM.yy").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            signSysStorage.setPassportParamSys(passportParamSysStorage);
            signSysStorage.setSortSign(typeSignalTableStorageService.getById(type));

            signSysStorageService.save(signSysStorage);

            redirectAttributes.addAttribute("message", "Данные датчика были успешно сохранены!");
        } else {
            redirectAttributes.addAttribute("error", "Данные о датчике не были найдены!");
        }

        return "redirect:/virtual/sensors";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editSensor (Model model, @PathVariable Integer id) {

        PassportParamSys passportParamSys = passportParamSysService.getById(id);

        if (passportParamSys == null || passportParamSys.getMeasParamTypeSig().getIdUstavka() < 4) {
            passportParamSys = new PassportParamSys();
        }

        model.addAttribute("sensor", passportParamSys);
        model.addAttribute("types", passportParamSysService.getRootComboItems());

        return "sensor";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSensor (Model model, @RequestParam(defaultValue = "-1") Integer id,
              @RequestParam String objMonitor, @RequestParam String name,
              @RequestParam String number, @RequestParam Integer measParamTypeSig,
              @RequestParam(required = false) Float value, @RequestParam(required = false) String date,
              @RequestParam(required = false) String time, @RequestParam(required = false) Double k1,
                              @RequestParam(required = false) Double k1Low, RedirectAttributes redirectAttributes) {

        PassportParamSys sensor;
        PassportParamSysStorage sensorStorage;
        if (id != -1) {
            sensor = passportParamSysService.getById(id);
            sensorStorage = passportParamSysStorageService.getById(id);
        } else {
            sensor = new PassportParamSys();
            sensorStorage = new PassportParamSysStorage();
        }

        sensor.setIdSensors(passportParamSysService.getLastId());
        sensor.setName(name);
        sensor.setObjMonitor(objMonitor);
        sensor.setNumber(number);
        sensor.setMeasParamTypeSig(ustavkaParamSysService.getById(measParamTypeSig));
        sensor.setCriter_release(k1Low);
        sensor.setCriterion(k1);

        sensor = passportParamSysService.save(sensor);


        TypeSignalTable typeSignalTable;

        if (value > k1) {
            typeSignalTable = typeSignalTableService.getById(2);
        } else {
            typeSignalTable = typeSignalTableService.getById(1);
        }

        SignSys signSys = new SignSys();
        try {
            signSys.setTimeSign(new SimpleDateFormat("dd.MM.yy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        signSys.setPassportParamSys(sensor);
        signSys.setSortSign(typeSignalTable);

        signSysService.save(signSys);


        MeasParamSys measParamSys = new MeasParamSys();
        measParamSys.setIdSensors(sensor.getIdSensors());
        measParamSys.setValueMeas(value);
        try {
            measParamSys.setDateMeas(new SimpleDateFormat("dd.MM.yy").parse(date));
            measParamSys.setTimeMeas(new SimpleDateFormat("hh:mm:ss").parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        measParamSysService.save(measParamSys);




        sensorStorage.setIdSensors(sensor.getIdSensors());
        sensorStorage.setName(name);
        sensorStorage.setObjMonitor(objMonitor);
        sensorStorage.setNumber(number);
        sensorStorage.setMeasParamTypeSig(ustavkaParamSysStorageService.getById(measParamTypeSig));

        sensorStorage = passportParamSysStorageService.save(sensorStorage);



        TypeSignalTableStorage typeSignalTableStorage;

        if (value > k1) {
            typeSignalTableStorage = typeSignalTableStorageService.getById(2);
        } else {
            typeSignalTableStorage = typeSignalTableStorageService.getById(1);
        }

        SignSysStorage signSysStorage = new SignSysStorage();
        try {
            signSysStorage.setTimeSign(new SimpleDateFormat("dd.MM.yy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        signSysStorage.setPassportParamSys(sensorStorage);
        signSysStorage.setSortSign(typeSignalTableStorage);

        signSysStorageService.save(signSysStorage);



        MeasParamSysStorage measParamSysStorage = new MeasParamSysStorage();
        measParamSysStorage.setPassportParamSys(sensorStorage);
        measParamSysStorage.setValueMeas(value);
        try {
            measParamSysStorage.setDateMeas(new SimpleDateFormat("dd.MM.yy").parse(date));
            measParamSysStorage.setTimeMeas(new SimpleDateFormat("hh:mm:ss").parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        measParamSysStorageService.save(measParamSysStorage);

        redirectAttributes.addAttribute("message", "Данные датчика были успешно сохранены!");

        return "redirect:/virtual/sensors";
    }

    @RequestMapping("/type/save")
    public String saveSensorType (Model model, @RequestParam(defaultValue = "-1", required = false) Integer id, @RequestParam String description) {

        UstavkaParamSys ustavkaParamSys;
        UstavkaParamSysStorage ustavkaParamSysStorage;
        if (id != null && id != -1) {
            ustavkaParamSys = ustavkaParamSysService.getById(id);
            ustavkaParamSysStorage = ustavkaParamSysStorageService.getById(id);
        } else {
            ustavkaParamSys = new UstavkaParamSys();
            ustavkaParamSysStorage = new UstavkaParamSysStorage();
        }

        ustavkaParamSys.setDiscription(description);
        ustavkaParamSysStorage.setDiscription(description);

        ustavkaParamSysService.save(ustavkaParamSys);
        ustavkaParamSysStorageService.save(ustavkaParamSysStorage);


        return "redirect:/virtual/sensors";
    }

}
