package com.tdorosz.drawiogen.drawio.shape;

import com.tdorosz.drawiogen.drawio.xmlschema.Diagram;
import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;

import java.util.ArrayList;
import java.util.List;

public class DrawioFile {
    private final List<DrawioPage> pages = new ArrayList<>();

    public DrawioPage addNewPage(String name) {
        DrawioPage page = new DrawioPage(name);
        pages.add(page);
        return page;
    }

    public MxFile toMxFile() {
        List<Diagram> diagrams = pages.stream()
                .map(DrawioPage::toDiagram)
                .toList();

        return new MxFile()
                .diagrams(diagrams);
    }
}
