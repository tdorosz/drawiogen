package com.tdorosz.drawiogen.drawio.xmlschema;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "mxfile")
public class MxFile {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "diagram")
    private List<Diagram> diagrams;
}
