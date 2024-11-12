package com.tdorosz.drawiogen.drawio.serialize;

import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;

@Slf4j
public class MxFileSerializer {

    private final JAXBContext jaxbContext;

    @SneakyThrows
    public MxFileSerializer() {
        jaxbContext = JAXBContext.newInstance(MxFile.class);
    }

    @SneakyThrows
    public String generateXml(MxFile mxFile) {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(mxFile, writer);
        return writer.toString();
    }

}
