
package api.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carPlaceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeOfCreated" type="{http://soap.api/}localDateTime" minOccurs="0"/>
 *         &lt;element name="type" type="{http://soap.api/}eventType" minOccurs="0"/>
 *         &lt;element name="zoneCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zoneId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
    "carCode",
    "carPlaceId",
    "description",
    "id",
    "timeOfCreated",
    "type",
    "zoneCode",
    "zoneId"
})
public class Event {

    protected String carCode;
    protected String carPlaceId;
    protected String description;
    protected String id;
    protected LocalDateTime timeOfCreated;
    @XmlSchemaType(name = "string")
    protected EventType type;
    protected String zoneCode;
    protected String zoneId;

    /**
     * Gets the value of the carCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarCode() {
        return carCode;
    }

    /**
     * Sets the value of the carCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarCode(String value) {
        this.carCode = value;
    }

    /**
     * Gets the value of the carPlaceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarPlaceId() {
        return carPlaceId;
    }

    /**
     * Sets the value of the carPlaceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarPlaceId(String value) {
        this.carPlaceId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the timeOfCreated property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getTimeOfCreated() {
        return timeOfCreated;
    }

    /**
     * Sets the value of the timeOfCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setTimeOfCreated(LocalDateTime value) {
        this.timeOfCreated = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EventType }
     *     
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventType }
     *     
     */
    public void setType(EventType value) {
        this.type = value;
    }

    /**
     * Gets the value of the zoneCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * Sets the value of the zoneCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneCode(String value) {
        this.zoneCode = value;
    }

    /**
     * Gets the value of the zoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * Sets the value of the zoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneId(String value) {
        this.zoneId = value;
    }

}
