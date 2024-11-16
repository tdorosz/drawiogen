package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxCell {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String value;

    @XmlAttribute
    private String style;

    @XmlAttribute
    private String vertex;

    @XmlAttribute
    private String collapsed;

    @XmlAttribute
    private String parent;

    @XmlAttribute
    private String source;

    @XmlAttribute
    private String target;

    @XmlAttribute
    private String edge;

    @XmlElement(name = "mxGeometry")
    private MxGeometry mxGeometry;

}
