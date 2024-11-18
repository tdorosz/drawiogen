package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.DrawioFile;
import com.tdorosz.drawiogen.drawio.element.DrawioPage;
import com.tdorosz.drawiogen.drawio.element.composite.ClassDetails;
import com.tdorosz.drawiogen.drawio.serialize.MxFileDeserializer;
import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import com.tdorosz.drawiogen.drawio.xmlschema.MxFile;
import com.tdorosz.drawiogen.usecase.ClassDescription;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
class ReadClassesTest {

    private static final String PROJECT_PATH = "D:\\projekty\\java\\drawiogen\\src";
    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-3.xml";
    private static final String FILE_PATH_OUT = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-3-out.xml";

    private MxFileSerializer mxFileSerializer = new MxFileSerializer();
    private MxFileDeserializer mxFileDeserializer = new MxFileDeserializer();

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
        DrawioFile drawioFile = DrawioFile.createNew();
        DrawioPage page = drawioFile.createPage("page1");

        int i = 0;
        for (Map.Entry<Path, String> entry : contentMap.entrySet()) {
            Path fileName = entry.getKey().getFileName();

            ClassDescription classDescription = new ClassDescription();

            Pattern logPattern = Pattern.compile("^\\s*(log\\.(error|debug|info)\\(.*\\);)$");

            List<String> list = entry.getValue().lines()
                    .filter(line -> logPattern.matcher(line).matches())
                    .map(String::trim)
                    .toList();

            classDescription
                    .addLogLines(list)
                    .classFullName(fileName.getFileName().toString())
                    .position((i % 10) * 500, (i / 10) * 300);

            page.addElement(classDescription);
            i++;
        }

        String xml = mxFileSerializer.generateXml(drawioFile.mxFile());
        Files.writeString(Path.of(FILE_PATH), xml);
    }


    @Test
    void addNewElement() throws IOException {
        MxFile mxFile = mxFileDeserializer.readXml(Files.readString(Path.of(FILE_PATH)));
        DrawioFile drawioFile = DrawioFile.from(mxFile);
        DrawioPage page1 = drawioFile.getPageByName("page1");
        DrawioElementModel elementById = page1.getElementById("f78967cb-19b6-4f27-aeca-81bc041b4cbc");
        ClassDetails classDetails = ClassDetails.from(elementById);
        ClassDescription classDescription = ClassDescription.from(classDetails);
        classDescription.getRenderer().getRoot().styleEditBegin()
                .fillColor(Color.magenta)
                .styleEditCommit();
        String xml = mxFileSerializer.generateXml(mxFile);
        log.info(xml);
        Files.writeString(Path.of(FILE_PATH_OUT), xml);
    }
}
