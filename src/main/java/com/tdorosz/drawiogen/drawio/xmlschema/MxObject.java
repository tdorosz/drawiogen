package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxObject {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String placeholders;

    @XmlAttribute
    private String label;

    @XmlAttribute
    private String tooltip;

    @XmlElement(name = "mxCell")
    private MxCell mxCell;

}
