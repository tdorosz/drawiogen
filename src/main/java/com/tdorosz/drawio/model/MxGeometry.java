package com.tdorosz.drawio.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MxGeometry {

    @JacksonXmlProperty(isAttribute = true)
    private Integer x;

    @JacksonXmlProperty(isAttribute = true)
    private Integer y;

    @JacksonXmlProperty(isAttribute = true)
    private Integer width;

    @JacksonXmlProperty(isAttribute = true)
    private Integer height;

    @JacksonXmlProperty(isAttribute = true)
    private String relative;

    @JacksonXmlProperty(isAttribute = true)
    private String as;

}
