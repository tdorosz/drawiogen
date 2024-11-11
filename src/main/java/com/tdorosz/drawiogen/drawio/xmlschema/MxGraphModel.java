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
public class MxGraphModel {

    @JacksonXmlProperty(isAttribute = true)
    private Integer pageWidth;

    @JacksonXmlProperty(isAttribute = true)
    private Integer pageHeight;

    private Root root;
}
