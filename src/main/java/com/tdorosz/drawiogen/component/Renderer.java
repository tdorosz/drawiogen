package com.tdorosz.drawiogen.component;

import com.tdorosz.drawiogen.drawio.shape.DrawioShape;

import java.util.List;

public interface Renderer {
    List<DrawioShape<?>> getShapes();
}
