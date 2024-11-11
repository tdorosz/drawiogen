package com.tdorosz.drawiogen.drawio.xmlschema.read;

import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class MxGraphModelRead {

    @XmlAnyAttribute
    private Map<String, String> arguments;

    @XmlElement(name = "root")
    private RootRead root;

}
