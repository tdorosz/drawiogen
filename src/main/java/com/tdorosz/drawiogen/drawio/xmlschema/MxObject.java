package com.tdorosz.drawiogen.drawio.xmlschema;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class MxObject {

    @XmlAnyAttribute
    private Map<QName, String> arguments;

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String placeholders;

    @XmlAttribute
    private String label;

    @XmlAttribute
    private String tooltip;

    @XmlAttribute
    private String complexElementType;

    @XmlElement(name = "mxCell")
    private MxCell mxCell;


    public MxObject arguments(Map<String, String> map) {
        if (map == null) {
            arguments = null;
        } else {
            arguments = new HashMap<>();
            map.forEach((key, value) -> arguments.put(new QName(key), value));
        }
        return this;
    }

    public MxObject addArgument(String key, String value) {
        if (arguments == null) {
            arguments = new HashMap<>();
        }
        if (value == null) {
            arguments.remove(new QName(key));
        } else {
            arguments.put(new QName(key), value);
        }
        return this;
    }

}
