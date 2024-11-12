package com.tdorosz.drawiogen.drawio.serialize;

import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;

@Slf4j
public class MxFileDeserializer {

    private final JAXBContext jaxbContext;

    @SneakyThrows
    public MxFileDeserializer() {
        jaxbContext = JAXBContext.newInstance(MxFile.class);
    }

    @SneakyThrows
    public MxFile readXml(String content) {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (MxFile) unmarshaller.unmarshal(new StringReader(content));
    }

}
