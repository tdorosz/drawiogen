package com.tdorosz.drawiogen.component;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;

import java.util.List;

public interface Renderer {
    List<DrawioElementModel> getModel();

    void parentId(String id);
}
