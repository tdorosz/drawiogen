package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.component.ClassDetailsRenderer;
import com.tdorosz.drawiogen.drawio.element.DrawioFile;
import com.tdorosz.drawiogen.drawio.element.DrawioPage;
import com.tdorosz.drawiogen.drawio.element.simple.Rectangle;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.serialize.MxFileDeserializer;
import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class CreateNewDiagramTest {

    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\fromScratch-1.xml";
    private static final String FILE_PATH_OUT = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\fromScratch-1-out.xml";

    private final MxFileDeserializer mxFileDeserializer = new MxFileDeserializer();
    private final MxFileSerializer serializer = new MxFileSerializer();

    @Test
    void createNewDiagramFromScratch() throws IOException {
        DrawioFile file = DrawioFile.createNew();
        DrawioPage page = file.createPage("TestPage 1").pageSize(200, 200);
        DrawioPage page2 = file.createPage("TestPage 2").pageSize(300, 300);
        DrawioPage drawioPage = page.addElement(Rectangle.createNew());

        file.getPageByName("TestPage 2").addElement(Rectangle.createNew()
                .styleEditBegin()
                .fillColor(DrawioColor.COLOR_LIGHTPINK)
                .styleEditCommit());

        Rectangle rectangle1 = Rectangle.createNew().position(100, 100);
        Rectangle rectangle2 = Rectangle.createNew().position(200, 200);

        page.addElement(rectangle1);
        page.addElement(rectangle2);

        ClassDetailsRenderer classDetailsRenderer = new ClassDetailsRenderer();

        page.addElement(classDetailsRenderer);

        String xml = serializer.generateXml(file.mxFile());
        log.info(xml);

        Files.writeString(Path.of(FILE_PATH), xml);

    }
}
