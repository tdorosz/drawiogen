package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

@Data
@Accessors(fluent = true, chain = true)
public class DrawioElementModel {
    private final String rootId;
    private final List<MxCell> mxCells;
    private final List<MxObject> mxObjects;

    public DrawioElementModel(String id, List<MxCell> mxCells, List<MxObject> mxObjects) {
        this.rootId = id;
        this.mxCells = new ArrayList<>(mxCells);
        this.mxObjects = new ArrayList<>(mxObjects);
    }

    public DrawioElementModel(String id, List<DrawioElementModel> models) {
        this.rootId = id;
        this.mxCells = new ArrayList<>();
        this.mxObjects = new ArrayList<>();
        for (DrawioElementModel model : models) {
            mxCells.addAll(model.mxCells());
            mxObjects.addAll(model.mxObjects);
        }
    }

    public MxCell cellById(String id) {
        return mxCells.stream()
                .filter(cell -> cell.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public MxObject objectById(String id) {
        return mxObjects.stream()
                .filter(obj -> obj.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public DrawioElementModel extractFromId(String id) {
        List<MxCell> filteredCells = new ArrayList<>();
        List<MxObject> filteredObjects = new ArrayList<>();

        mxCells.stream().filter(cell -> id.equals(cell.id()))
                .forEach(filteredCells::add);

        mxObjects.stream().filter(obj -> id.equals(obj.id()))
                .forEach(filteredObjects::add);

        Deque<String> ids = new ArrayDeque<>();
        ids.add(id);
        while (!ids.isEmpty()) {
            String processingId = ids.pop();
            mxCells.stream().filter(cell -> processingId.equals(cell.parent())).forEach(cell -> {
                filteredCells.add(cell);
                ids.add(cell.id());
            });

            mxObjects.stream().filter(obj -> processingId.equals(obj.mxCell().parent())).forEach(obj -> {
                filteredObjects.add(obj);
                ids.add(obj.id());
            });
        }

        return new DrawioElementModel(id, filteredCells, filteredObjects);
    }

    public DrawioElementModel filterByComplexElementPart(String partName) {
        MxObject root = mxObjects.stream().filter(obj -> rootId.equals(obj.mxCell().parent()))
                .filter(obj -> partName.equals(obj.complexElementType()))
                .findFirst()
                .orElse(null);

        if (root == null) {
            return null;
        }

        return extractFromId(root.id());
    }
}
