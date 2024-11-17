package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.DrawioPage;
import com.tdorosz.drawiogen.drawio.element.Group;
import com.tdorosz.drawiogen.drawio.element.simple.Rectangle;
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
        Group group2 = new Group().parent(group.id());

        Rectangle rectangle = new Rectangle()
                .parent(group2.id())
                .value("test1 - val2")
                .addAlternateBounds(100, 20)
                .styleEditBegin()
                .collapsible(BinaryState.ON)
                .styleEditCommit();

        rectangle.styleEditBegin()
                .html(BinaryState.ON)
                .rounded(BinaryState.ON)
                .sketch(BinaryState.ON)
                .editable(BinaryState.ON)
                .movable(BinaryState.ON)
                .fillColor(DrawioColor.COLOR_POWDERBLUE)
                .enumerate(BinaryState.ON)
                .enumerateValue("No way=10;20;20")
                .rounded(BinaryState.ON)
                .styleEditCommit();

        DrawioPage drawioPage = DrawioPage.createPage("First!")
                .pageSize(500, 500)
                .addElement(rectangle);

        MxFile test = new MxFile().diagrams(List.of(drawioPage.mxDiagram()));

        String xml = serializer.generateXml(test);
        log.info(xml);

        Files.writeString(Path.of(FILE_PATH), xml);
    }

    @Test
    void updateRectangle() throws IOException {
        MxFile mxFile = mxFileDeserializer.readXml(Files.readString(Path.of(FILE_PATH)));

        DrawioPage drawioPage = new DrawioPage(mxFile.diagrams().get(0));

        DrawioElementModel element = drawioPage.getElementById("dba40ca8-17e8-4246-bfee-6d77e139c4a3");

        Rectangle rectangle = Rectangle.from(element);

        rectangle
                .value("Hello Test")
                .styleEditBegin()
                .glass(BinaryState.ON)
                .fillColor(DrawioColor.fromColor(DrawioColor.COLOR_RED))
                .rounded(BinaryState.ON)
                .enumerateValue("Hi")
                .enumerate(BinaryState.OFF)
                .styleEditCommit();

        String xml = serializer.generateXml(mxFile);

        log.info(xml);

        Files.writeString(Path.of(FILE_PATH_OUT), xml);
    }

}
