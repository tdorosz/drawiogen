package com.tdorosz.drawiogen.component;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.shape.DrawioShape;
import com.tdorosz.drawiogen.drawio.shape.basic.Rectangle;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class ClassDetailsRenderer implements Renderer {

    private static final int PARENT_WIDTH = 300;
    private static final int PARENT_HEIGHT = 100;
    private static final int BOTTOM_RIGHT_INFO_WIDTH = PARENT_WIDTH / 5;
    private static final int BOTTOM_RIGHT_INFO_HEIGHT = PARENT_HEIGHT / 5;
    private static final int TAB_INFO_HEIGHT = 20;
    private static final int TAB_INFO_WIDTH = PARENT_WIDTH / 2;

    private static final int CONTAINER_TOP_RIGHT_WIDTH = PARENT_WIDTH / 3;
    private static final int CONTAINER_TOP_RIGHT_HEIGHT = 20;

    private final Rectangle parent;
    private final Rectangle bottomRightRectangle;
    private final Rectangle tabInfo;
    private final Rectangle containerTopRight;

    public ClassDetailsRenderer() {
        parent = Rectangle.newRectangle(0, 0)
                .width(PARENT_WIDTH)
                .height(PARENT_HEIGHT)
                .style()
                .styleEnd();

        bottomRightRectangle = Rectangle.newRectangle(PARENT_WIDTH - BOTTOM_RIGHT_INFO_WIDTH, PARENT_HEIGHT - BOTTOM_RIGHT_INFO_HEIGHT)
                .parent(parent.id())
                .width(BOTTOM_RIGHT_INFO_WIDTH)
                .height(BOTTOM_RIGHT_INFO_HEIGHT)
                .style()
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_ORANGERED))
                .editable(BinaryState.OFF)
                .connectable(BinaryState.OFF)
                .styleEnd();

        tabInfo = Rectangle.newRectangle(0, -TAB_INFO_HEIGHT)
                .parent(parent.id())
                .width(TAB_INFO_WIDTH)
                .height(TAB_INFO_HEIGHT);

        containerTopRight = Rectangle.newRectangle(PARENT_WIDTH - CONTAINER_TOP_RIGHT_WIDTH, 0)
                .parent(parent.id())
                .width(CONTAINER_TOP_RIGHT_WIDTH)
                .height(TAB_INFO_HEIGHT);
    }

    public List<DrawioShape<?>> getShapes() {
        return Stream.<DrawioShape<?>>of(parent, bottomRightRectangle, tabInfo, containerTopRight)
                .filter(DrawioShape::shouldAdd)
                .toList();
    }

}
