package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxGeometry {

    @XmlAnyAttribute
    private Map<String, Object> arguments;

    @XmlAttribute
    private Integer x;

    @XmlAttribute
    private Integer y;

    @XmlAttribute
    private Integer width;

    @XmlAttribute
    private Integer height;

    @XmlAttribute
    private String relative;

    @XmlAttribute
    private String as;

}
