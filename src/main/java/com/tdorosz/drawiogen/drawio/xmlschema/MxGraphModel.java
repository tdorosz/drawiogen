package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxGraphModel {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlAttribute(name = "host")
    private Integer pageWidth;

    @XmlAttribute(name = "host")
    private Integer pageHeight;

    @XmlElement(name = "root")
    private MxRoot root;
}
