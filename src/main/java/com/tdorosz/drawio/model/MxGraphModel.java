package com.tdorosz.drawio.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "mxGraphModel")
public class MxGraphModel {
    @JacksonXmlElementWrapper(localName = "root")
    @JacksonXmlProperty(localName = "mxCell")
    private List<MxCell> cells;

    public MxGraphModel addCell(MxCell mxCell) {
        cells = Stream.concat(cells.stream(), Stream.of(mxCell)).toList();
        return this;
    }
}
