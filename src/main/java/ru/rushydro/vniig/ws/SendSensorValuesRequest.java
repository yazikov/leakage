//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.04 at 02:38:48 PM MSK 
//


package ru.rushydro.vniig.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensorValues" type="{http://i-sensor/webservice}sensorValues"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sensorValues"
})
@XmlRootElement(name = "sendSensorValuesRequest")
public class SendSensorValuesRequest {

    @XmlElement(required = true)
    protected SensorValues sensorValues;

    /**
     * Gets the value of the sensorValues property.
     * 
     * @return
     *     possible object is
     *     {@link SensorValues }
     *     
     */
    public SensorValues getSensorValues() {
        return sensorValues;
    }

    /**
     * Sets the value of the sensorValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorValues }
     *     
     */
    public void setSensorValues(SensorValues value) {
        this.sensorValues = value;
    }

}
