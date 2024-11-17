package com.tdorosz.drawiogen.usecase;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.DrawioElementModelProvider;
import com.tdorosz.drawiogen.drawio.element.composite.ClassDetails;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.formatter.DeaultLogLinesFormatter;
import com.tdorosz.drawiogen.formatter.LogLinesFormatter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ClassDescription implements DrawioElementModelProvider<ClassDescription> {

    @Getter
    private final ClassDetails renderer = new ClassDetails();
    private final List<String> logLines = new ArrayList<>();

    private LogLinesFormatter logLinesFormatter = new DeaultLogLinesFormatter();

    public ClassDescription() {
        renderer.getBottomRightRectangle()
                .value("Logs")
                .styleEditBegin()
                .editable(BinaryState.OFF)
                .styleEditCommit();
    }

    public ClassDescription addLogLines(List<String> logLines) {
        this.logLines.addAll(logLines);
        updateLogs();
        return this;
    }

    public ClassDescription classFullName(String classFullName) {
        this.renderer.getRoot().value(classFullName);
        if (classFullName.matches(".*Test.java")) {
            this.renderer.getTabInfo().value("Test")
                    .styleEditBegin()
                    .fillColor(DrawioColor.COLOR_YELLOW)
                    .styleEditCommit();
        } else if (classFullName.matches(".*[Dd]rawio.*")) {
            this.renderer.getTabInfo().value("Drawio")
                    .styleEditBegin().fillColor(DrawioColor.COLOR_TEAL)
                    .styleEditCommit();
        }
        return this;
    }

    @Override
    public DrawioElementModel getDrawioElementModel() {
        return renderer.getDrawioElementModel();
    }

    @Override
    public ClassDescription parent(String id) {
        renderer.parent(id);
        return this;
    }

    private void updateLogs() {
        String logsTooltipText = logLinesFormatter.format(logLines);
        renderer.getBottomRightRectangle()
                .tooltip(logsTooltipText)
                .value("Logs: %s".formatted(logLines.size()));
    }
}
