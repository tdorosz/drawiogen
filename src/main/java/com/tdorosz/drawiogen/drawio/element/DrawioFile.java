package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;

import java.util.ArrayList;

public class DrawioFile {
    private MxFile mxFile;

    public DrawioFile(MxFile mxFile) {
        this.mxFile = mxFile;
    }

    public static DrawioFile createNew() {
        MxFile mxFile = new MxFile();
        mxFile.diagrams(new ArrayList<>());

        return new DrawioFile(mxFile);
    }

    public MxFile mxFile() {
        return mxFile;
    }

    public DrawioPage getPageByName(String name) {
        return mxFile.diagrams().stream()
                .filter(mxDiagram -> name.equals(mxDiagram.name()))
                .findFirst()
                .map(DrawioPage::new)
                .orElse(null);
    }

    public DrawioPage getPageById(String id) {
        return mxFile.diagrams().stream()
                .filter(mxDiagram -> id.equals(mxDiagram.id()))
                .findFirst()
                .map(DrawioPage::new)
                .orElse(null);
    }

    public DrawioPage createPage(String pageName) {
        DrawioPage page = DrawioPage.createPage(pageName);
        mxFile.diagrams().add(page.mxDiagram());
        return page;
    }
}
