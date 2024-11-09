package com.tdorosz.drawio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DrawioGenerator {

    private final ObjectMapper mapper;

    @SneakyThrows
    public String generateXml(Object mxCell) {
        return mapper.writeValueAsString(mxCell);
    }
}
