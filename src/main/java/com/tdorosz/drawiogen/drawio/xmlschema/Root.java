package com.tdorosz.drawiogen.drawio.xmlschema;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Root {

    @JacksonXmlProperty(localName = "mxCell")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<MxCell> cells;

    @JacksonXmlProperty(localName = "object")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ObjectWrapper> objects;

    public Root addCell(MxCell mxCell) {
        if (cells == null) {
            cells = List.of();
        }
        cells = Stream.concat(cells.stream(), Stream.of(mxCell)).toList();
        return this;
    }

    public Root addObject(ObjectWrapper objectWrapper) {
        if (objects == null) {
            objects = List.of();
        }
        objects = Stream.concat(objects.stream(), Stream.of(objectWrapper)).toList();
        return this;
    }
}
