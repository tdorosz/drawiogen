package com.tdorosz.drawiogen.component;

import com.tdorosz.drawiogen.drawio.shape.BinaryState;
import com.tdorosz.drawiogen.drawio.shape.DrawioColor;
import com.tdorosz.drawiogen.drawio.shape.DrawioShape;
import com.tdorosz.drawiogen.drawio.shape.basic.Rectangle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassDetailsRenderer implements Renderer {

    private static final String LOGS_KEY = "logs";

    @Getter
    private final Rectangle bottomRightRectangle;

    private Map<String, DrawioShape<?>> shapeMap = new HashMap<>();

    private List<String> logs = new ArrayList<>();

    public ClassDetailsRenderer() {
        Rectangle main = Rectangle.newRectangle(0, 0)
                .width(200)
                .height(100)
                .value("Main");

        int infoWidth = main.width() / 5;
        int infoHeight = main.height() / 5;

        bottomRightRectangle = Rectangle.newRectangle(main.width() - infoWidth, main.height() - infoHeight)
                .parent(main.id())
                .width(infoWidth)
                .height(infoHeight)
                .style()
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_ORANGERED))
                .editable(BinaryState.OFF)
                .connectable(BinaryState.OFF)
                .styleEnd();

        shapeMap.put("main", main);
        shapeMap.put(LOGS_KEY, bottomRightRectangle);
    }

    public ClassDetailsRenderer addLogLine(String line) {
        logs.add(line);
        updateLogTooltip();
        return this;
    }

    private void updateLogTooltip() {
        Rectangle logsPart = (Rectangle) shapeMap.get(LOGS_KEY);

        String collect = logs.stream()
                .map(log -> {
                    if (log.contains("error")) {
                        return """
                                <span style="color:red;">%s</span>
                                """.formatted(log);
                    }
                    return """
                                <span style="color:blue;">%s</span>
                                """.formatted(log);
                })
                .collect(Collectors.joining(""));

        logsPart.tooltip("""
                        <pre>
                        %s
                        </pre>
                        """.formatted(collect));


    }

    public List<DrawioShape<?>> getShapes() {
        return shapeMap.values()
                .stream().toList();
    }

}
