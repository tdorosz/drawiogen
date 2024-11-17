package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.element.Group;
import com.tdorosz.drawiogen.drawio.element.Rectangle;
import com.tdorosz.drawiogen.drawio.element.RootContainer;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.serialize.MxFileDeserializer;
import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import com.tdorosz.drawiogen.drawio.xmlschema.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class CreateElementTests {

    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\rectangle-1.xml";
    private static final String FILE_PATH_OUT = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\rectangle-1-out.xml";

    private final MxFileDeserializer mxFileDeserializer = new MxFileDeserializer();
    private final MxFileSerializer serializer = new MxFileSerializer();

    @Test
    void createRectangle() throws IOException {
        RootContainer container = new RootContainer();
        Group group = new Group().parent(container.id());

        Rectangle rectangle = new Rectangle()
                .parent(group.id())
                .value("test1 - val2")
                .addAlternateBounds(100, 20)
                .styleEditBegin()
                .collapsible(BinaryState.ON)
                .styleEditCommit();

        rectangle.styleEditBegin()
                .html(BinaryState.ON)
                .rounded(BinaryState.ON)
                .sketch(BinaryState.ON)
                .editable(BinaryState.OFF)
                .movable(BinaryState.ON)
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_POWDERBLUE))
                .styleEditCommit();

        MxFile test = new MxFile()
                .diagrams(List.of(
                        new MxDiagram().name("test")
                                .mxGraphModel(new MxGraphModel()
                                        .pageHeight(200).pageWidth(200)
                                        .root(new MxRoot()
                                                .cells(List.of(container.getMxCell(), group.getMxCell(), rectangle.mxCell()))
                                                .objects(List.of())
                                        ))
                ));

        String xml = serializer.generateXml(test);
        log.info(xml);

        Files.writeString(Path.of(FILE_PATH), xml);
    }

    @Test
    void updateRectangle() throws IOException {
        MxFile mxFile = mxFileDeserializer.readXml(Files.readString(Path.of(FILE_PATH)));

        List<MxObject> cells = mxFile.diagrams().get(0).mxGraphModel().root().objects();
        MxObject element = cells.stream()
                .filter(cell -> cell.id().equals("82822cd3-f28f-4cb4-8339-95ded7f36d62")).findFirst().get();

        Rectangle rectangle = new Rectangle(element);
        rectangle
                .value("Hello Test")
                .styleEditBegin()
                .glass(BinaryState.ON)
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_RED))
                .rounded(BinaryState.ON)
                .styleEditCommit();

        String xml = serializer.generateXml(mxFile);

        log.info(xml);

        Files.writeString(Path.of(FILE_PATH_OUT), xml);
    }

}
