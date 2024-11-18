package com.tdorosz.drawiogen.drawio.element.composite;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.DrawioElementModelProvider;
import com.tdorosz.drawiogen.drawio.element.simple.Rectangle;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ClassDetails implements DrawioElementModelProvider<ClassDetails> {

    private static final int PARENT_WIDTH = 300;
    private static final int PARENT_HEIGHT = 100;
    private static final int BOTTOM_RIGHT_INFO_WIDTH = PARENT_WIDTH / 5;
    private static final int BOTTOM_RIGHT_INFO_HEIGHT = PARENT_HEIGHT / 5;
    private static final int TAB_INFO_HEIGHT = 20;
    private static final int TAB_INFO_WIDTH = PARENT_WIDTH / 2;

    private static final int CONTAINER_TOP_RIGHT_WIDTH = PARENT_WIDTH / 3;
    private static final int CONTAINER_TOP_RIGHT_HEIGHT = 20;

    private static final String COMPLEX_ELEMENT_TYPE_ROOT = "ClassDetails-root";
    private static final String COMPLEX_ELEMENT_TYPE_BOTTOM_RIGHT_RECTANGLE = "ClassDetails-bottomRightRectangle";
    private static final String COMPLEX_ELEMENT_TYPE_TAB_INFO = "ClassDetails-tabInfo";
    private static final String COMPLEX_ELEMENT_TYPE_CONTAINER_TOP_RIGHT = "ClassDetails-containerTopRight";

    public static ClassDetails from(DrawioElementModel elementModel) {

        return new ClassDetails(
                Rectangle.from(elementModel),
                Rectangle.from(elementModel.filterByComplexElementPart(COMPLEX_ELEMENT_TYPE_BOTTOM_RIGHT_RECTANGLE)),
                Rectangle.from(elementModel.filterByComplexElementPart(COMPLEX_ELEMENT_TYPE_TAB_INFO)),
                Rectangle.from(elementModel.filterByComplexElementPart(COMPLEX_ELEMENT_TYPE_CONTAINER_TOP_RIGHT))
        );
    }

    @Override
    public ClassDetails parent(String id) {
        root.parent(id);
        return this;
    }

    @Override
    public DrawioElementModel getDrawioElementModel() {
        return new DrawioElementModel(root.id(),
                List.of(root.getDrawioElementModel(),
                        bottomRightRectangle.getDrawioElementModel(),
                        tabInfo.getDrawioElementModel(),
                        containerTopRight.getDrawioElementModel()));
    }

    private final Rectangle root;
    private final Rectangle bottomRightRectangle;
    private final Rectangle tabInfo;
    private final Rectangle containerTopRight;

    public ClassDetails() {
        root = new Rectangle()
                .complexElementType(COMPLEX_ELEMENT_TYPE_ROOT)
                .position(0, 0)
                .size(PARENT_WIDTH, PARENT_HEIGHT)
                .styleEditBegin()
                .styleEditCommit();

        bottomRightRectangle = new Rectangle()
                .complexElementType(COMPLEX_ELEMENT_TYPE_BOTTOM_RIGHT_RECTANGLE)
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
                .complexElementType(COMPLEX_ELEMENT_TYPE_TAB_INFO)
                .position(0, -TAB_INFO_HEIGHT)
                .parent(root.id())
                .width(TAB_INFO_WIDTH)
                .height(TAB_INFO_HEIGHT);

        containerTopRight = new Rectangle()
                .complexElementType(COMPLEX_ELEMENT_TYPE_CONTAINER_TOP_RIGHT)
                .position(PARENT_WIDTH - CONTAINER_TOP_RIGHT_WIDTH, 0)
                .parent(root.id())
                .width(CONTAINER_TOP_RIGHT_WIDTH)
                .height(TAB_INFO_HEIGHT);
    }

}
