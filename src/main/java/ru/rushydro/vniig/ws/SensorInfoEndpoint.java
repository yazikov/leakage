package ru.rushydro.vniig.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.service.MeasParamSysService;
import ru.rushydro.vniig.service.PassportParamSysService;
import ru.rushydro.vniig.service.SignSysService;
import ru.rushydro.vniig.storage.service.MeasParamSysStorageService;
import ru.rushydro.vniig.storage.service.SignSysStorageService;

import java.util.List;

/**
 * Created by nikolay on 12.09.15.
 */
//@Endpoint
public class SensorInfoEndpoint {
//    private static final String NAMESPACE_URI = "http://i-sensor/webservice";
//
//    @Autowired
//    MeasParamSysService measParamSysService;
//
//    @Autowired
//    MeasParamSysStorageService measParamSysStorageService;
//
//    @Autowired
//    PassportParamSysService passportParamSysService;
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSensorValuesRequest")
//    @ResponsePayload
//    public GetSensorValuesResponse getSensorInfoValues(@RequestPayload GetSensorValuesRequest request) {
//        int type = request.getType();
//        GetSensorValuesResponse response = new GetSensorValuesResponse();
//        List<PassportParamSys> passportParamSysList = null;
//        if (type != 1 && type != 2 && type != 3) {
//            passportParamSysList = passportParamSysService.getSensors();
//        } else {
//            passportParamSysList = passportParamSysService.getSensorByType(type);
//        }
//
//        SensorInfoValues sensorValues = new SensorInfoValues();
//
//        if (passportParamSysList != null) {
//            for (PassportParamSys sensor : passportParamSysList) {
//                SensorInfoValue sensorInfoValue = new SensorInfoValue();
//                sensorInfoValue.setSensorId(sensor.getIdSensors());
//                sensorInfoValue.setSensorNumber(sensor.getNumber());
//                sensorInfoValue.setSensorParameter(sensor.getTypeOfSensor());
//                sensorInfoValue.setSensorValue(sensor.getMeasParamSys().getValueMeas());
//                sensorValues.getSensorValue().add(sensorInfoValue);
//            }
//        }
//
//        response.setSensorValues(sensorValues);
//        return response;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendLevelRequest")
//    @ResponsePayload
//    public SendLevelResponse sendLevel(@RequestPayload SendLevelRequest request) {
//        double level = request.getLevel();
//        SendLevelResponse sendLevelResponse = new SendLevelResponse();
//
//        if (measParamSysService.updateLevel(level) && measParamSysStorageService.insertLevel(level)) {
//            sendLevelResponse.setStatusCode(1);
//            sendLevelResponse.setStatusDescription("Значение установлено.");
//        } else {
//            sendLevelResponse.setStatusCode(-1);
//            sendLevelResponse.setStatusDescription("Ошибка при установке уровня.");
//        }
//
//        return sendLevelResponse;
//    }

}
