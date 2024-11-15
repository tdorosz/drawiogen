package com.tdorosz.drawiogen.drawio.util;

import io.micrometer.common.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StyleParser {
    public static Map<String, String> parse(String style) {
        if (StringUtils.isBlank(style)) {
            return Map.of();
        }

        return Arrays.stream(style.split(";"))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> parts.length > 1 ? parts[1] : "",
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }
}
