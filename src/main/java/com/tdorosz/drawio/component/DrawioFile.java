package com.tdorosz.drawio.component;

import com.tdorosz.drawio.model.Diagram;
import com.tdorosz.drawio.model.MxFile;

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

        return MxFile.builder()
                .diagrams(diagrams)
                .build();
    }
}
