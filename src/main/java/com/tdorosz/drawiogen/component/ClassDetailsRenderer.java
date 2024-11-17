package com.tdorosz.drawiogen.component;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.simple.Rectangle;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import lombok.Getter;

import java.util.List;

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

    @Override
    public List<DrawioElementModel> getModel() {
        return List.of(root.toModel(), bottomRightRectangle.toModel(), tabInfo.toModel());
    }

    private final Rectangle root;
    private final Rectangle bottomRightRectangle;
    private final Rectangle tabInfo;
    private final Rectangle containerTopRight;

    public ClassDetailsRenderer() {
        root = new Rectangle()
                .position(0, 0)
                .size(PARENT_WIDTH, PARENT_HEIGHT)
                .styleEditBegin()
                .styleEditCommit();

        bottomRightRectangle = new Rectangle()
                .position(PARENT_WIDTH - BOTTOM_RIGHT_INFO_WIDTH, PARENT_HEIGHT - BOTTOM_RIGHT_INFO_HEIGHT)
                .parent(root.id())
                .width(BOTTOM_RIGHT_INFO_WIDTH)
                .height(BOTTOM_RIGHT_INFO_HEIGHT)
                .styleEditBegin()
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_ORANGERED))
                .editable(BinaryState.OFF)
                .connectable(BinaryState.OFF)
                .movable(BinaryState.OFF)
                .styleEditCommit();

        tabInfo = new Rectangle()
                .position(0, -TAB_INFO_HEIGHT)
                .parent(root.id())
                .width(TAB_INFO_WIDTH)
                .height(TAB_INFO_HEIGHT);

        containerTopRight = new Rectangle()
                .position(PARENT_WIDTH - CONTAINER_TOP_RIGHT_WIDTH, 0)
                .parent(root.id())
                .width(CONTAINER_TOP_RIGHT_WIDTH)
                .height(TAB_INFO_HEIGHT);
    }

    @Override
    public void parentId(String id) {
        root.parent(id);
    }
}
