package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.component.ClassDetailsRenderer;
import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import com.tdorosz.drawiogen.drawio.shape.DrawioFile;
import com.tdorosz.drawiogen.drawio.shape.DrawioPage;
import com.tdorosz.drawiogen.drawio.shape.basic.Arrow;
import com.tdorosz.drawiogen.drawio.shape.basic.Rectangle;
import com.tdorosz.drawiogen.usecase.ClassDescription;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tdorosz.drawiogen.drawio.shape.DrawioColor.*;
import static com.tdorosz.drawiogen.drawio.shape.basic.Process.newProcess;

@Slf4j
class ReadClassesTest {

    private static final String PROJECT_PATH = "D:\\projekty\\java\\drawiogen\\src\\main\\java";
    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-3.xml";

    private MxFileSerializer mxFileSerializer = new MxFileSerializer();

    @Test
    void createShapeForEachClass() throws IOException {
        Path root = Paths.get(PROJECT_PATH);

        Map<Path, String> contentMap = new HashMap<>();

        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    log.info("Found java file {}", file);
                    String content = String.join("\n", Files.readAllLines(file));
                    contentMap.put(file, content);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        log.info("Ready for processing!");
        DrawioFile drawioFile = new DrawioFile();
        DrawioPage page1 = drawioFile.addNewPage("Page1");


        int i = 0;
        String prevId = null;
        for (Map.Entry<Path, String> entry : contentMap.entrySet()) {
            String fileName = entry.getKey().getFileName().toString();

            Rectangle rectangle = page1.addElement(Rectangle.newRectangle(i * 120, 0)
                    .value(fileName.replace(".java", ""))
                    .addCustomValue("fullClassName", entry.getKey().toString())
                    .style()
                    .fillColor(entry.getKey().getParent().endsWith("basic") ? fromColor(COLOR_MEDIUMSLATEBLUE) : fromColor(COLOR_BROWN))
                    .styleEnd());

            if (prevId != null) {
                page1.addElement(Arrow.newArrow(prevId, rectangle.id()));
            }
            prevId = rectangle.id();

            log.info(fileName);
            ++i;
        }

        page1.addElement(newProcess(200, 200));

        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
        Files.writeString(Path.of(FILE_PATH), xml);
    }

    @Test
    void complexShapes_logs() throws IOException {
        DrawioFile drawioFile = new DrawioFile();
        DrawioPage page1 = drawioFile.addNewPage("Page1");

        ClassDetailsRenderer classDetailsRenderer = new ClassDetailsRenderer();

        classDetailsRenderer.getShapes().forEach(page1::addElement);
        classDetailsRenderer.addLogLine("log.debug(...)");
        classDetailsRenderer.addLogLine("log.error(...)");
        classDetailsRenderer.addLogLine("log.debug(...)");
        classDetailsRenderer.addLogLine("log.error(...)");
        classDetailsRenderer.addLogLine("log.debug(...)");
//        page1.addElement(newProcess(200, 200));

        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
        Files.writeString(Path.of(FILE_PATH), xml);
    }

    @Test
    void example_usecase() throws IOException {
        DrawioFile drawioFile = new DrawioFile();
        DrawioPage page1 = drawioFile.addNewPage("Page1");

        List<String> logLines = new ArrayList<>();
        logLines.add("log.debug(...)");
        logLines.add("log.error(...)");
        logLines.add("log.debug(...)");
        logLines.add("log.error(...)");
        logLines.add("log.debug(...)");

        ClassDescription classDescription = new ClassDescription();
        classDescription.addLogLines(logLines);

        page1.addUseCase(classDescription);

        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
        Files.writeString(Path.of(FILE_PATH), xml);
    }
}
