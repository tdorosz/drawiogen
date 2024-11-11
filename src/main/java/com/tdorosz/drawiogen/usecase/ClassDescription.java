package com.tdorosz.drawiogen.usecase;

import com.tdorosz.drawiogen.component.ClassDetailsRenderer;
import com.tdorosz.drawiogen.drawio.shape.BinaryState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassDescription implements UseCase {

    @Getter
    private final ClassDetailsRenderer renderer = new ClassDetailsRenderer();
    private final List<String> logLines = new ArrayList<>();

    public ClassDescription() {
        renderer.getBottomRightRectangle()
                .value("Logs")
                .style()
                .editable(BinaryState.OFF)
                .styleEnd();
    }

    public void addLogLines(List<String> logLines) {
        this.logLines.addAll(logLines);
        updateLogs();
    }

    private void updateLogs() {
        String logsTooltipText = createLogsTooltip();
        renderer.getBottomRightRectangle()
                .tooltip(logsTooltipText);
    }

    private String createLogsTooltip() {
        String collect = logLines.stream()
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

        return """
                <pre>
                %s
                </pre>
                """.formatted(collect);
    }
}
