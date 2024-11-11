package com.tdorosz.drawiogen.usecase;

import com.tdorosz.drawiogen.component.ClassDetailsRenderer;
import com.tdorosz.drawiogen.drawio.shape.BinaryState;
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

    public ClassDescription() {
        renderer.getBottomRightRectangle()
                .value("Logs")
                .style()
                .editable(BinaryState.OFF)
                .styleEnd();
    }

    public ClassDescription addLogLines(List<String> logLines) {
        this.logLines.addAll(logLines);
        updateLogs();
        return this;
    }

    public ClassDescription classFullName(String classFullName) {
        this.renderer.getParent().value(classFullName);
        return this;
    }

    private void updateLogs() {
        String logsTooltipText = logLinesFormatter.format(logLines);
        renderer.getBottomRightRectangle()
                .tooltip(logsTooltipText);
    }

    public void position(int x, int y) {
        renderer.getParent()
                .x(x)
                .y(y);
    }

    public String id() {
        return renderer.getParent().id();
    }
}
