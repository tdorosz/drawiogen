package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

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
}
