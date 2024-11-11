package com.tdorosz.drawiogen.drawio.xmlschema;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MxCell {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlProperty(isAttribute = true)
    private String value;

    @JacksonXmlProperty(isAttribute = true)
    private String style;

    @JacksonXmlProperty(isAttribute = true)
    private String vertex;

    @JacksonXmlProperty(isAttribute = true)
    private String parent;

    @JacksonXmlProperty(isAttribute = true)
    private String source;

    @JacksonXmlProperty(isAttribute = true)
    private String target;

    @JacksonXmlProperty(isAttribute = true)
    private String edge;

    private MxGeometry mxGeometry;

}
