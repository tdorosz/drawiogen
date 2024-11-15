package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlRootElement(name = "mxfile")
@XmlAccessorType(XmlAccessType.FIELD)
public class MxFile {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlAttribute(name = "agent")
    private String agent;

    @XmlAttribute(name = "host")
    private String host;

    @XmlAttribute(name = "version")
    private String version;

    @XmlElement(name = "diagram")
    private List<MxDiagram> diagrams;
}
