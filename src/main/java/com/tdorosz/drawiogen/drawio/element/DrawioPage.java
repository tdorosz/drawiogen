package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DrawioPage {
    private final MxDiagram mxDiagram;
    private final RootContainer rootContainer;
    private final Group mainGroup;

    public DrawioPage(MxDiagram mxDiagram) {
        this.mxDiagram = mxDiagram;
        List<MxCell> cells = mxDiagram.mxGraphModel().root().cells();
        rootContainer = cells.stream()
                .map(MxCell::parent)
                .filter(Objects::nonNull)
                .findFirst()
                .map(RootContainer::new)
                .orElse(null);

        mainGroup = cells.stream()
                .filter(x -> rootContainer.id().equals(x.parent()))
                .findFirst()
                .map(cell -> new Group().id(cell.id()).parent(cell.parent()))
                .orElse(null);

    }

    public static DrawioPage createPage(String pageName) {
        RootContainer rootContainer = new RootContainer();
        Group group = new Group().parent(rootContainer.id());

        List<MxCell> cells = new ArrayList<>();
        cells.add(rootContainer.getMxCell());
        cells.add(group.getMxCell());

        MxDiagram mxDiagram = new MxDiagram()
                .name(pageName)
                .mxGraphModel(new MxGraphModel()
                        .root(new MxRoot()
                                .cells(cells)
                                .objects(new ArrayList<>())
                                .arguments(new HashMap<>())));

        return new DrawioPage(mxDiagram);
    }

    public MxDiagram mxDiagram() {
        return mxDiagram;
    }

    public MxGraphModel mxGraphModel() {
        return mxDiagram.mxGraphModel();
    }

    public DrawioPage name(String name) {
        mxDiagram.name(name);
        return this;
    }

    public MxRoot mxRoot() {
        return mxDiagram.mxGraphModel().root();
    }

    public DrawioPage addElement(DrawioElementModelProvider<?> modelProvider) {
        modelProvider.parent(mainGroup.id());
        DrawioElementModel drawioElementModel = modelProvider.getDrawioElementModel();
        mxRoot().objects().addAll(drawioElementModel.mxObjects());
        mxRoot().cells().addAll(drawioElementModel.mxCells());
        return this;
    }

    public DrawioPage pageSize(int width, int height) {
        mxGraphModel()
                .pageWidth(width)
                .pageHeight(height);
        return this;
    }

    public DrawioElementModel getElementById(String id) {
        List<MxCell> allMxCells = new ArrayList<>();
        List<MxObject> allMxObjects = new ArrayList<>();

        Optional.ofNullable(mxRoot().cells()).orElse(List.of()).stream()
                .filter(mxCell -> id.equals(mxCell.id()))
                .forEach(allMxCells::add);

        Optional.ofNullable(mxRoot().objects()).orElse(List.of()).stream()
                .filter(mxObject -> id.equals(mxObject.id()))
                .forEach(allMxObjects::add);

        Deque<String> ids = new ArrayDeque<>();
        ids.add(id);

        while (!ids.isEmpty()) {
            String processingId = ids.pop();
            Optional.ofNullable(mxRoot().cells()).orElse(List.of()).stream()
                    .filter(mxCell -> processingId.equals(mxCell.parent()))
                    .forEach(mxCell -> {
                        allMxCells.add(mxCell);
                        ids.add(mxCell.id());
                    });

            Optional.ofNullable(mxRoot().objects()).orElse(List.of()).stream()
                    .filter(mxObject -> processingId.equals(mxObject.mxCell().parent()))
                    .forEach(mxObject -> {
                        allMxObjects.add(mxObject);
                        ids.add(mxObject.id());
                    });
            log.info("Processing {}", processingId);
        }

        return new DrawioElementModel(id, allMxCells, allMxObjects);
    }
}
