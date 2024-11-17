package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.element.Group;
import com.tdorosz.drawiogen.drawio.element.RectangleCell;
import com.tdorosz.drawiogen.drawio.element.RectangleObject;
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

        RectangleCell rectangle = RectangleCell.createNew()
                .parent(group.id())
                .value("test1")
                .addAlternateBounds(300, 300)
                .styleEditBegin()
                .collapsible(BinaryState.ON)
                .styleEditCommit();

        RectangleCell rectangle2 = RectangleCell.from(rectangle.mxCell());
        rectangle2.styleEditBegin()
                .html(BinaryState.ON)
                .styleEditCommit();

        rectangle.addStyle("html=0").styleEditBegin()
                .rounded(BinaryState.ON)
                .sketch(BinaryState.ON)
                .styleEditCommit();

        RectangleObject rectangleObject = RectangleObject.createNew()
                .parent(group.id())
                .styleEditBegin()
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_SANDYBROWN))
                .glass(BinaryState.ON)
                .styleEditCommit();

        MxFile test = new MxFile()
                .diagrams(List.of(
                        new MxDiagram().name("test")
                                .mxGraphModel(new MxGraphModel()
                                        .pageHeight(200).pageWidth(200)
                                        .root(new MxRoot()
                                                .cells(List.of(container.getMxCell(), group.getMxCell()))
                                                .objects(List.of(rectangleObject.mxObject()))
                                        ))
                ));

        String xml = serializer.generateXml(test);
        log.info(xml);

        Files.writeString(Path.of(FILE_PATH), xml);
    }

    @Test
    void updateRectangle() throws IOException {
        MxFile mxFile = mxFileDeserializer.readXml(Files.readString(Path.of(FILE_PATH)));

        List<MxCell> cells = mxFile.diagrams().get(0).mxGraphModel().root().cells();
        MxCell mxCell = cells.stream()
                .filter(cell -> cell.id().equals("07d90513-74b1-4ca4-ab84-c2dadc0fab5e")).findFirst().get();

        RectangleCell rectangle = RectangleCell.from(mxCell);
        rectangle.styleEditBegin()
                .glass(BinaryState.ON)
                .rounded(null)
                .styleEditCommit();

        String xml = serializer.generateXml(mxFile);

        log.info(xml);

        Files.writeString(Path.of(FILE_PATH_OUT), xml);
    }
}
