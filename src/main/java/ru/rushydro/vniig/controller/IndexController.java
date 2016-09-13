package ru.rushydro.vniig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rushydro.vniig.entry.*;
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

        TypeSignalTable first = new TypeSignalTable();
        first.setIdSignal(1);
        first.setTextSignal("Норма");

        MeasParamSys firstMeasParamSys = new MeasParamSys();
        firstMeasParamSys.setWorkSensors(true);
        firstMeasParamSys.setValueMeas(1000f);

        SignSys firstSignSys = new SignSys();
        firstSignSys.setSortSign(first);

        TypeSignalTable second = new TypeSignalTable();
        second.setIdSignal(2);
        second.setTextSignal("Предаварийное состояние");

        MeasParamSys secondMeasParamSys = new MeasParamSys();
        secondMeasParamSys.setWorkSensors(true);
        secondMeasParamSys.setValueMeas(2000f);

        SignSys secondSignSys = new SignSys();
        secondSignSys.setSortSign(second);

        TypeSignalTable third = new TypeSignalTable();
        third.setIdSignal(3);
        third.setTextSignal("Аварийное состояние");

        MeasParamSys thirdMeasParamSys = new MeasParamSys();
        thirdMeasParamSys.setWorkSensors(true);
        thirdMeasParamSys.setValueMeas(3000f);

        SignSys thirdSignSys = new SignSys();
        thirdSignSys.setSortSign(third);


        int y = 147;
        for (int j = 0; j < 4; j++) {
            int index = 2;
            if (j == 1) {
                index = 11;
            } else if (j == 2) {
                index = 20;
            } else if (j == 3) {
                index = 29;
            }
            int x = 253;
            for (int i = 0; i < 8; i++) {
                if (j < 2 || i < 7) {
                    SensorRegion region = new SensorRegion();
                    region.setId(index);
                    region.setTitle(numberToLatin(region.getId()));
                    region.setX(x);
                    if (i == 0) {
                        region.setWidth(82);
                    } else if (i == 5) {
                        region.setWidth(84);
                    } else if (i == 3) {
                        region.setWidth(81);
                    }

                    region.setY(y + region.getHeight() * j);
                    regions.add(region);
                    for (int g = 0; g < 3; g++) {
                        for (int c = 0; c < 4; c++) {
                            Block block = new Block();
                            block.setX(region.getX() + 1 + 20 * c);
                            block.setY(region.getY() + 1 + 21 * g);
                            block.setWidth(c != 3 ? 20 : region.getWidth() - 2 - 20 * 3);
                            block.setHeight(g != 2 ? 21 : region.getHeight() - 2 - 21 * 2);
                            block.setId(g * 4 + c + 1);
                            block.setCriterion(1500d);
                            switch (block.getId()%3) {
                                case 0: block.setMeasParamSys(firstMeasParamSys); block.setSignSys(firstSignSys); break;
                                case 1: block.setMeasParamSys(secondMeasParamSys); block.setSignSys(secondSignSys); break;
                                case 2: block.setMeasParamSys(thirdMeasParamSys); block.setSignSys(thirdSignSys); break;
                            }
                            block.setTitle("" + block.getId());
                            region.getBlocks().add(block);
                        }
                    }
                    x += region.getWidth();
                    if (i > 3) {
                        x -= 1;
                    }
                    System.out.println("Insert into passport_sectors values(" + region.getId() + ",'"+region.getTitle()+"',"+region.getX()+","+region.getY()+","+region.getWidth()+","+region.getHeight()+");");

                    index++;
                }
            }
        }

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

    public String numberToLatin(Integer number) {
        String text = "";
        switch (number) {
            case 1 : text = "I"; break;
            case 2 : text = "II"; break;
            case 3 : text = "III"; break;
            case 4 : text = "IV"; break;
            case 5 : text = "V"; break;
            case 6 : text = "VI"; break;
            case 7 : text = "VII"; break;
            case 8 : text = "VIII"; break;
            case 9 : text = "IX"; break;
            case 10 : text = "X"; break;
            case 11 : text = "XI"; break;
            case 12 : text = "XII"; break;
            case 13 : text = "XIII"; break;
            case 14 : text = "XIV"; break;
            case 15 : text = "XV"; break;
            case 16 : text = "XVI"; break;
            case 17 : text = "XVII"; break;
            case 18 : text = "XVIII"; break;
            case 19 : text = "XIX"; break;
            case 20 : text = "XX"; break;
            case 21 : text = "XXI"; break;
            case 22 : text = "XXII"; break;
            case 23 : text = "XXIII"; break;
            case 24 : text = "XXIV"; break;
            case 25 : text = "XXV"; break;
            case 26 : text = "XXVI"; break;
            case 27 : text = "XXVII"; break;
            case 28 : text = "XXVIII"; break;
            case 29 : text = "XXIX"; break;
            case 30 : text = "XXX"; break;
            case 31 : text = "XXXI"; break;
            case 32 : text = "XXXII"; break;
            case 33 : text = "XXXIII"; break;
            case 34 : text = "XXXIV"; break;
            case 35 : text = "XXXV"; break;
            case 36 : text = "XXXVI"; break;
            case 37 : text = "XXXVII"; break;
            case 38 : text = "XXXVIII"; break;
            case 39 : text = "XXXIX"; break;
        }
        return text;
    }
}
