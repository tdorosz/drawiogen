package com.tdorosz.drawiogen.drawio.xmlschema.read;

import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class RootRead {

    @XmlAnyAttribute
    private Map<String, Object> arguments;

    @XmlElement(name = "object")
    private List<ObjectRead> objects;

    @XmlElement(name = "mxCell")
    private List<MxCellRead> mxCellReads;

}
