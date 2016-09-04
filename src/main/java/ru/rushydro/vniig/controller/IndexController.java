package ru.rushydro.vniig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rushydro.vniig.entry.Insision;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.entry.SensorRegion;
import ru.rushydro.vniig.service.InsisionService;
import ru.rushydro.vniig.service.PassportParamSysService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 06.09.15.
 */
@Controller
@RequestMapping(path = "/")
public class IndexController {
    final static Logger log = Logger.getLogger(IndexController.class);

    @Autowired
    PassportParamSysService sensorService;

    @Autowired
    InsisionService insisionService;
    
    @RequestMapping(path = "/")
    public String indexPage(Model model) {

        List<SensorRegion> regions = new ArrayList<>();
        SensorRegion region = new SensorRegion();
        region.setId(1);
        region.setTitle("Разрез 1");
        region.setCoord("101,233,265,234,266,148,81,148");
        regions.add(region);

        region = new SensorRegion();
        region.setId(2);
        region.setTitle("Разрез 2");
        region.setCoord("267,147,267,271,431,271,431,148");
        regions.add(region);

        region = new SensorRegion();
        region.setId(3);
        region.setTitle("Разрез 3");
        region.setCoord("432,147,431,271,598,270,596,146");
        regions.add(region);

        region = new SensorRegion();
        region.setId(4);
        region.setTitle("Разрез 4");
        region.setCoord("597,147,597,271,803,270,803,148");
        regions.add(region);

        region = new SensorRegion();
        region.setId(5);
        region.setTitle("Разрез 5");
        region.setCoord("803,146,803,269,941,270,996,180,996,131,939,145");
        regions.add(region);

        region = new SensorRegion();
        region.setId(6);
        region.setTitle("Разрез 6");
        region.setCoord("144,404,102,234,268,235,267,405");
        regions.add(region);

        region = new SensorRegion();
        region.setId(7);
        region.setTitle("Разрез 7");
        region.setCoord("267,271,267,404,431,403,432,272");
        regions.add(region);

        region = new SensorRegion();
        region.setId(8);
        region.setTitle("Разрез 8");
        region.setCoord("432,270,432,403,596,403,597,269");
        regions.add(region);

        region = new SensorRegion();
        region.setId(9);
        region.setTitle("Разрез 9");
        region.setCoord("596,271,598,402,804,404,804,272");
        regions.add(region);

        region = new SensorRegion();
        region.setId(10);
        region.setTitle("Разрез 10");
        region.setCoord("804,271,803,407,855,406,939,271");
        regions.add(region);

        //Переделать на получение из базы данных
        model.addAttribute("regions", regions);


//        List<String> roots = sensorService.getAllRootNodes();
//        List<PassportParamSys> passportParamSysList = sensorService.getSensorByType(1);
//
//        List<Insision> insisions = insisionService.getAllInsision();
//
//        model.addAttribute("roots", roots);
//        model.addAttribute("sensors", passportParamSysList);
//        model.addAttribute("insisions", insisions);
        return "index";
    }
}
