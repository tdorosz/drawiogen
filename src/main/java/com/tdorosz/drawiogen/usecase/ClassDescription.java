package com.tdorosz.drawiogen.usecase;

import com.tdorosz.drawiogen.component.ClassDetailsRenderer;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.formatter.DeaultLogLinesFormatter;
import com.tdorosz.drawiogen.formatter.LogLinesFormatter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ClassDescription implements UseCase {

    @Getter
    private final ClassDetailsRenderer renderer = new ClassDetailsRenderer();
    private final List<String> logLines = new ArrayList<>();

    private LogLinesFormatter logLinesFormatter = new DeaultLogLinesFormatter();
//
//    public ClassDescription() {
//        renderer.getBottomRightRectangle()
//                .value("Logs")
//                .style()
//                .editable(BinaryState.OFF)
//                .styleEnd();
//    }
//
//    public ClassDescription addLogLines(List<String> logLines) {
//        this.logLines.addAll(logLines);
//        updateLogs();
//        return this;
//    }
//
//    public ClassDescription classFullName(String classFullName) {
//        this.renderer.getParent().value(classFullName);
//        if (classFullName.matches(".*Test.java")) {
//            this.renderer.getTabInfo().value("Test")
//                    .style().fillColor(DrawioColor.fromColor(DrawioColor.COLOR_YELLOW));
//        } else if (classFullName.matches(".*[Dd]rawio.*")) {
//            this.renderer.getTabInfo().value("Drawio")
//                    .style().fillColor(DrawioColor.fromColor(DrawioColor.COLOR_TEAL));
//        }
//        return this;
//    }
//
//    private void updateLogs() {
//        String logsTooltipText = logLinesFormatter.format(logLines);
//        renderer.getBottomRightRectangle()
//                .tooltip(logsTooltipText)
//                .value("Logs: %s".formatted(logLines.size()));
//    }
//
//    public void position(int x, int y) {
//        renderer.getParent()
//                .x(x)
//                .y(y);
//    }
//
//    public String id() {
//        return renderer.getParent().id();
//    }
}
