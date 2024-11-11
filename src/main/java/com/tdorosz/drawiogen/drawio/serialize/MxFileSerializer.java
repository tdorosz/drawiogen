package com.tdorosz.drawiogen.drawio.serialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MxFileSerializer {

    private static final Pattern OBJECT_START_PATTERN = Pattern.compile("^\\s*<object.*>$");
    private static final Pattern OBJECT_END_PATTERN = Pattern.compile("^\\s*<object.*/>$");
    private static final Pattern CUSTOM_PARAMS_PATTERN = Pattern.compile("^\\s*<customParams\\s(.*)/>$");
    private static final Pattern EMPTY_PARAMS_PATTERN = Pattern.compile("^\\s*<customParams/>$");

    private final XmlMapper mapper;

    public MxFileSerializer() {
        mapper = new XmlMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @SneakyThrows
    public String generateXml(MxFile mxFile) {
        String xml = mapper.writeValueAsString(mxFile);
        xml = adjustObjectCustomParams(xml);
        return xml;
    }

    @SneakyThrows
    private String adjustObjectCustomParams(String xml) {
        String[] lines = xml.split("\n");

        int lastObjectLine = -1;
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("\\s+$", "");
            Matcher emptyCustomParamsMatcher = EMPTY_PARAMS_PATTERN.matcher(lines[i]);
            if (emptyCustomParamsMatcher.matches()) {
                lines[i] = "";
            }
            Matcher objectStartMatcher = OBJECT_START_PATTERN.matcher(lines[i]);
            if (objectStartMatcher.matches()) {
                lastObjectLine = i;
            }
            Matcher objectEndMatcher = OBJECT_END_PATTERN.matcher(lines[i]);
            if (objectEndMatcher.matches()) {
                lastObjectLine = -1;
            }

            Matcher matcher = CUSTOM_PARAMS_PATTERN.matcher(lines[i]);
            if (matcher.matches()) {
                if (lastObjectLine == -1) {
                    throw new UnexpectedException("Some problems! Find me please");
                }
                lines[lastObjectLine] = lines[lastObjectLine].replaceFirst(">", " %s>".formatted(matcher.group(1)));
                lines[i] = "";
            }
        }

        return Arrays.stream(lines)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\r\n"));
    }
}
