package com.tdorosz.drawiogen.drawio.xmlschema.read;

import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Accessors(fluent = true, chain = true)
@NoArgsConstructor
@XmlRootElement(name = "mxfile")
public class MxFileRead {

    @XmlAnyAttribute
    private Map<String, Object> arguments;

    @XmlAttribute(name = "agent")
    private String agent;

    @XmlAttribute(name = "host")
    private String host;

    @XmlAttribute(name = "version")
    private String version;

    @XmlElement(name = "diagram")
    private DiagramRead diagramRead;

}
