package com.tdorosz.drawiogen.formatter;

import java.util.List;
import java.util.stream.Collectors;

public class DeaultLogLinesFormatter implements LogLinesFormatter {

    @Override
    public String format(List<String> logLines) {
        String collect = logLines.stream()
                .map(log -> {
                    if (log.contains("log.debug")) {
                        return colorLog(log, "blue");
                    } else if (log.contains("log.error")) {
                        return colorLog(log, "red");
                    }
                    return colorLog(log, "green");
                })
                .collect(Collectors.joining(""));

        return wrapInPreTag(collect);
    }

    private static String wrapInPreTag(String collect) {
        return "<pre>%s</pre>".formatted(collect);
    }

    private static String colorLog(String log, String color) {
        return """
                <span style="color:%s;">%s</span>
                """.formatted(color, log);
    }
}
