package com.tdorosz.drawiogen.drawio.shape;

import com.tdorosz.drawiogen.component.Renderer;
import com.tdorosz.drawiogen.drawio.xmlschema.*;
import com.tdorosz.drawiogen.usecase.UseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class DrawioPage {

    private final String id = UUID.randomUUID().toString();
    private final String mainCellId = UUID.randomUUID().toString();
    private final String subCellId = UUID.randomUUID().toString();

    private String name;
    private Integer pageWidth;
    private Integer pageHeight;

    private List<DrawioShape> elements = new ArrayList<>();

    public DrawioPage(String name) {
        this.name = name;
    }

    public Diagram toDiagram() {
        List<ObjectWrapper> objectWrappers = elements.stream()
                .filter(DrawioShape::shouldMapToObjectWrapper)
                .map(DrawioShape::toObjectWrapper)
                .toList();

        List<MxCell> cells = elements.stream()
                .filter(not(DrawioShape::shouldMapToObjectWrapper))
                .map(DrawioShape::toMxCell)
                .toList();

        List<MxCell> standardCells = List.of(
                new MxCell()
                        .id(mainCellId),
                new MxCell()
                        .id(subCellId)
                        .parent(mainCellId));

        List<MxCell> allCells = Stream.concat(standardCells.stream(), cells.stream()).toList();


        return new Diagram()
                .id(id)
                .name(name)
                .mxGraphModel(new MxGraphModel()
                        .pageHeight(pageHeight)
                        .pageWidth(pageWidth)
                        .root(new Root().cells(allCells).objects(objectWrappers))
                );
    }

    public void resize(int width, int height) {
        pageWidth = width;
        pageHeight = height;
    }

    public <T extends DrawioShape> T addElement(T element) {
        if (element.parent() == null) {
            element.parent(subCellId);
        }
        elements.add(element);
        return element;
    }

    public void addUseCase(UseCase useCase) {
        Renderer renderer = useCase.getRenderer();
        List<DrawioShape<?>> shapes = renderer.getShapes();
        for (DrawioShape<?> shape : shapes) {
            if (shape.parent() == null) {
                shape.parent(subCellId);
            }
        }
        elements.addAll(shapes);
    }
}
