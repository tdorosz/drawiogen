package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.serialize.MxFileDeserializer;
import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class MxFileDeserializerTest {

    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-3.xml";
    private static final String FILE_PATH_WRITE = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-4.xml";

    private final MxFileDeserializer mxFileDeserializer = new MxFileDeserializer();
    private final MxFileSerializer serializer = new MxFileSerializer();

    @Test
    void shouldDeserializeMxFile() throws IOException, JAXBException {
//        MxFile mxFileRead = mxFileDeserializer.readXml(Files.readString(Paths.get(FILE_PATH)));
//        System.out.println(mxFileRead);

//        String result = serializer.generateXml(mxFileRead);
//        Files.write(Paths.get(FILE_PATH_WRITE), result.getBytes(StandardCharsets.UTF_8));
//
        JAXBContext context = JAXBContext.newInstance(MxFile.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        MxFile mxFileReadJax = (MxFile) unmarshaller.unmarshal(new File(FILE_PATH));

        System.out.println(mxFileReadJax);

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(mxFileReadJax, new File(FILE_PATH_WRITE));
    }
}
