package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxDiagram {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "mxGraphModel")
    private MxGraphModel mxGraphModel;
}
