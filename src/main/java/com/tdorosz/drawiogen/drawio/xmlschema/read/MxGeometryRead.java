package com.tdorosz.drawiogen.drawio.xmlschema.read;

import jakarta.xml.bind.annotation.XmlAnyAttribute;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class MxGeometryRead {

    @XmlAnyAttribute
    private Map<String, Object> arguments;

}
