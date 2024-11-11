package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import com.tdorosz.drawiogen.drawio.shape.DrawioFile;
import com.tdorosz.drawiogen.drawio.shape.DrawioPage;
import com.tdorosz.drawiogen.drawio.shape.basic.Arrow;
import com.tdorosz.drawiogen.drawio.shape.basic.Rectangle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DrawioFileTest {

    private static final String FILE_PATH = "D:\\projekty\\java\\drawio\\src\\test\\resources\\examples\\generated-new-model.xml";

    private MxFileSerializer mxFileSerializer = new MxFileSerializer();

    @Test
    void shouldCreateFile() throws IOException {
        DrawioFile drawioFile = new DrawioFile();
        DrawioPage page1 = drawioFile.addNewPage("Page1");
        page1.resize(120, 180);
        DrawioPage page2 = drawioFile.addNewPage("Page2");
        page2.resize(500, 800);
        DrawioPage page3 = drawioFile.addNewPage("Page3");
        page3.resize(500, 500);

        Rectangle rectangle = page1.addElement(Rectangle.newRectangle(100, 200));
        rectangle.addCustomValue("fullClassName", "com.tdorosz.Main2")
                .placeholders(false)
                .value("%fullClassName%");

        page2.addElement(Rectangle.newRectangle(100, 100).value("test"));

        Rectangle r1 = page3.addElement(Rectangle.newRectangle(100, 100).value("test"));
        Rectangle r2 = page3.addElement(Rectangle.newRectangle(200, 200).value("test"));
        page3.addElement(Arrow.newArrow(r1.id(), r2.id()));

        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
        Files.writeString(Path.of(FILE_PATH), xml);
    }
}
