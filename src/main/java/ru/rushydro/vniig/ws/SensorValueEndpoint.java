package ru.rushydro.vniig.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.rushydro.vniig.dao.MeasParamSysDAO;
import ru.rushydro.vniig.dao.SignSysDAO;
import ru.rushydro.vniig.entry.Parametr;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.service.MeasParamSysService;
import ru.rushydro.vniig.service.ParametrService;
import ru.rushydro.vniig.service.PassportParamSysService;
import ru.rushydro.vniig.service.SignSysService;
import ru.rushydro.vniig.storage.entry.MeasParamSysStorage;
import ru.rushydro.vniig.storage.service.MeasParamSysStorageService;
import ru.rushydro.vniig.storage.service.SignSysStorageService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 12.09.15.
 */
@Endpoint
public class SensorValueEndpoint {
    private static final String NAMESPACE_URI = "http://i-sensor/webservice";

    @Autowired
    MeasParamSysService measParamSysService;

    @Autowired
    SignSysService signSysService;

    @Autowired
    MeasParamSysStorageService measParamSysStorageService;

    @Autowired
    SignSysStorageService signSysStorageService;

    @Autowired
    ParametrService parametrService;


    @Autowired
    PassportParamSysService passportParamSysService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendSensorValuesRequest")
    @ResponsePayload
    public SendSensorValuesResponse getSensorValues(@RequestPayload SendSensorValuesRequest request) {
        SendSensorValuesResponse response = new SendSensorValuesResponse();
        response.setStatusCode(1);
        response.setStatusDescription("");

        try {
            if (request != null
                    && request.getSensorValues() != null
                    && request.getSensorValues().getSensorValue() != null) {
                parametrService.update("send", 0l);
                parametrService.update("send_all", 0l);
                for (SensorValue sensorValue : request.getSensorValues().getSensorValue()) {
                    System.out.println("Sensor ID: " + sensorValue.getSensorId() + " sensor value: " + sensorValue.getSensorValue());
                    measParamSysService.updateValue((int) sensorValue.getSensorId(), sensorValue.getSensorValue());
                    signSysService.updateValues((int) sensorValue.getSensorId(), sensorValue.getSensorValue());

                    measParamSysStorageService.insertValue((int) sensorValue.getSensorId(), sensorValue.getSensorValue());
                    signSysStorageService.insertValues((int) sensorValue.getSensorId(), sensorValue.getSensorValue());
                }
            } else {
                response.setStatusCode(0);
                response.setStatusDescription("Ошибка сохранения данных в базу данных!");
            }
        } catch (Exception e) {
            response.setStatusCode(0);
            response.setStatusDescription("Ошибка сохранения данных в базу данных: " + e.getMessage());
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSensorValuesRequest")
    @ResponsePayload
    public GetSensorValuesResponse getSensorInfoValues(@RequestPayload GetSensorValuesRequest request) {
        int type = request.getType();
        GetSensorValuesResponse response = new GetSensorValuesResponse();
        try {
            List<PassportParamSys> passportParamSysList;
            if (type == 1) {
                Parametr parametr = parametrService.findById("send_all");
                if (parametr.getValue().equals(1l)) {
                    response.setStatusCode(2);
                    response.setStatusDescription("Запрашиваемые данные уже были направлены. Новых данных не поступало.");
                    return response;
                }

                passportParamSysList = passportParamSysService.getSensorByType(1);
            } else if (type == 2) {
                Parametr parametr = parametrService.findById("send");
                if (parametr.getValue().equals(1l)) {
                    response.setStatusCode(2);
                    response.setStatusDescription("Запрашиваемые данные уже были направлены. Новых данных не поступало.");
                    return response;
                }
                passportParamSysList = passportParamSysService.getSensorByType(1).stream().filter(sensor -> sensor.getType() == 2).collect(Collectors.toList());
            } else {
                response.setStatusCode(0);
                response.setStatusDescription("Неверно задан тип данных! 1 для всех датчиков, 2 для превышающих уровень k1. ");
                return response;
            }

            SensorInfoValues sensorValues = new SensorInfoValues();


            if (passportParamSysList != null) {
                for (PassportParamSys sensor : passportParamSysList) {
                    SensorInfoValue sensorInfoValue = new SensorInfoValue();
                    sensorInfoValue.setSensorId(sensor.getIdSensors());
                    sensorInfoValue.setSensorNumber(sensor.getNumber());
                    sensorInfoValue.setSensorParameter(sensor.getTypeOfSensor());
                    sensorInfoValue.setSensorValue(sensor.getMeasParamSys().getValueMeas());
                    sensorValues.getSensorValue().add(sensorInfoValue);
                }
                response.setStatusCode(1);
                if (type == 1) {
                    parametrService.update("send_all", 1l);
                } else {
                    parametrService.update("send", 1l);
                }

            } else {
                response.setStatusCode(0);
                response.setStatusDescription("Ошибка получения данных!");
            }

            response.setSensorValues(sensorValues);
        } catch (Exception e) {
            response.setStatusCode(0);
            response.setStatusDescription("Ошибка получения данных: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendLevelRequest")
    @ResponsePayload
    public SendLevelResponse sendLevel(@RequestPayload SendLevelRequest request) {
        double level = request.getLevel();
        SendLevelResponse sendLevelResponse = new SendLevelResponse();
        try {

            if (measParamSysService.updateLevel(level) && measParamSysStorageService.insertLevel(level)) {
                sendLevelResponse.setStatusCode(1);
                sendLevelResponse.setStatusDescription("Значение установлено.");
            } else {
                sendLevelResponse.setStatusCode(0);
                sendLevelResponse.setStatusDescription("Ошибка при установке уровня!");
            }
        } catch (Exception e) {
            sendLevelResponse.setStatusCode(0);
            sendLevelResponse.setStatusDescription("Ошибка при установке уровня: " + e.getMessage());
        }

        return sendLevelResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendCurrentTimeRequest")
    @ResponsePayload
    public SendCurrentTimeResponse sendCurrentTime(@RequestPayload SendCurrentTimeRequest request) {
        XMLGregorianCalendar date = request.getTime();
        SendCurrentTimeResponse sendCurrentTimeResponse = new SendCurrentTimeResponse();
        try {

            sendCurrentTimeResponse.setStatusCode(1);
            sendCurrentTimeResponse.setStatusDescription("Значение установлено.");

        } catch (Exception e) {
            sendCurrentTimeResponse.setStatusCode(0);
            sendCurrentTimeResponse.setStatusDescription("Ошибка при установке уровня: " + e.getMessage());
        }

        return sendCurrentTimeResponse;
    }

}
