package ru.rushydro.vniig.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.entry.MeasParamSys;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.model.GraphicItem;
import ru.rushydro.vniig.model.GraphicModel;
import ru.rushydro.vniig.model.GraphicPoint;
import ru.rushydro.vniig.service.PassportParamSysService;
import ru.rushydro.vniig.storage.dao.MeasParamSysStorageDAO;
import ru.rushydro.vniig.storage.entry.MeasParamSysStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nikolay on 07.11.15.
 */
@Service
public class MeasParamSysStorageService extends AbstractStorageService<MeasParamSysStorage, MeasParamSysStorageDAO> {
    @Autowired
    public PassportParamSysService passportParamSysService;

    @Autowired
    public MeasParamSysStorageService(MeasParamSysStorageDAO dao) {
        super(dao);
    }

    public void insertValue (Integer id, Double value) {
        dao.insertValue(id, value);
    }

    public boolean insertLevel(double level) {
        return dao.insertLevel(level);
    }

    public List<MeasParamSysStorage> filter (String startDate, String endDate, List<Integer> sensors) {
        return dao.filter(startDate, endDate, sensors);
    }


    public GraphicModel getGraphic(String startDate, String endDate, List<Integer> sensors) {
        GraphicModel graphicModel = new GraphicModel();
        Map<Integer, GraphicItem> map = new HashMap<>();
        Double min = null;
        Double max = null;

        List<MeasParamSysStorage> list = filter(startDate, endDate, sensors);

        for (MeasParamSysStorage sensor : list) {
            String name = sensor.getPassportParamSys().getNumber();
            GraphicItem item = map.get(sensor.getPassportParamSys().getIdSensors());
            if (item == null) {
                item = new GraphicItem();
                if (name != null && !name.isEmpty()) {
                    item.setName(name + (sensor.getPassportParamSys().getIdSensors() % 2 == 1 ? " У" : " Т"));
                } else {
                    item.setName("Уровень верхнего бьефа");
                }
                map.put(sensor.getPassportParamSys().getIdSensors(), item);
            }

            GraphicPoint point = new GraphicPoint();
            point.setX(sensor.getDateMeas().getTime() + sensor.getTimeMeas().getTime());
            double y = new BigDecimal(sensor.getValueMeas()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            point.setY(y);
            item.getPoints().add(point);

            if (min == null || min > y) {
                min = y;
            }
            if (max == null || max < y) {
                max = y;
            }
        }

        graphicModel.setMinY(min != null ? min : 0);
        graphicModel.setMaxY(max != null ? max : 1);
        graphicModel.setItems(new ArrayList<>(map.values()));
        graphicModel.setStartDay(startDate);
        graphicModel.setEndDay(endDate);

        //Убраны линии k1 и k2

//        List<PassportParamSys> passportParamSyses = passportParamSysService.getSensorByType(1);
//        if (passportParamSyses != null && !passportParamSyses.isEmpty()) {
//            PassportParamSys sensor = passportParamSyses.get(0);
//            Long minX = graphicModel.getMinX();
//            Long maxX = graphicModel.getMaxX();
//
//            GraphicItem graphicItem = new GraphicItem();
//            graphicItem.setName("k1");
//            graphicItem.setColor("yellow");
//            GraphicPoint graphicPoint = new GraphicPoint();
//            graphicPoint.setX(minX);
//            graphicPoint.setY(sensor.getMeasParamTypeSig().getValueUstavkaPre());
//            graphicItem.getPoints().add(graphicPoint);
//
//            graphicPoint = new GraphicPoint();
//            graphicPoint.setX(maxX);
//            graphicPoint.setY(sensor.getMeasParamTypeSig().getValueUstavkaPre());
//            graphicItem.getPoints().add(graphicPoint);
//            graphicModel.getItems().add(graphicItem);
//
//            graphicItem = new GraphicItem();
//            graphicItem.setName("k2");
//            graphicItem.setColor("red");
//            graphicPoint = new GraphicPoint();
//            graphicPoint.setX(minX);
//            graphicPoint.setY(sensor.getMeasParamTypeSig().getValueUstavkaAv());
//            graphicItem.getPoints().add(graphicPoint);
//
//            graphicPoint = new GraphicPoint();
//            graphicPoint.setX(maxX);
//            graphicPoint.setY(sensor.getMeasParamTypeSig().getValueUstavkaAv());
//            graphicItem.getPoints().add(graphicPoint);
//            graphicModel.getItems().add(graphicItem);
//
//        }

        return graphicModel;
    }

    public boolean onSensor(Integer id) {
        return dao.onSensor(id);
    }

    public boolean offSensor(Integer id) {
        return dao.offSensor(id);
    }

    public MeasParamSysStorage save(MeasParamSysStorage measParamSysStorage) {
        return dao.save(measParamSysStorage);
    }
}
