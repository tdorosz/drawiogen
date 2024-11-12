package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlElement(name = "mxCell")
    private List<MxCell> cells;

    @XmlElement(name = "object")
    private List<ObjectWrapper> objects;
}
