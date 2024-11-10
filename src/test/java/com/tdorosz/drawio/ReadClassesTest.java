package com.tdorosz.drawio;

import com.tdorosz.drawio.component.basic.Rectangle;
import com.tdorosz.drawio.model.*;
import com.tdorosz.drawio.service.DrawioGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tdorosz.drawio.component.DrawioColor.COLOR_MEDIUMSLATEBLUE;
import static com.tdorosz.drawio.component.DrawioColor.fromColor;

@SpringBootTest
@Slf4j
class ReadClassesTest {

    private static final String PROJECT_PATH = "D:\\projekty\\java\\drawio\\src\\main\\java";
    private static final String FILE_PATH = "D:\\projekty\\java\\drawio\\src\\test\\resources\\examples\\generated-3.xml";
    private static final String FILE_PATH_MX_FILE = "D:\\projekty\\java\\drawio\\src\\test\\resources\\examples\\generated-mxfile.xml";

    @Autowired
    private DrawioGenerator drawioGenerator;

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
        MxGraphModel model = createBaseGraph();

        int i = 0;
        for (Map.Entry<Path, String> entry : contentMap.entrySet()) {
            String fileName = entry.getKey().getFileName().toString();
            Rectangle classRect = Rectangle.newRectangle(i * 120, 0)
                    .value(fileName)
                    .style()
                    .fillColor(fromColor(COLOR_MEDIUMSLATEBLUE))
                    .styleEnd();
//            model.addCell(classRect.toMxCell());
//            model.addObject(classRect.toObjectWrapper());
            log.info(fileName);
            ++i;
        }

        String xml = drawioGenerator.generateXml(model);
        Files.writeString(Path.of(FILE_PATH), xml);

    }

    @Test
    void generateMxFile() throws IOException {
        MxFile mxfile = MxFile.builder()
                .diagrams(List.of(Diagram.builder()
                                .id("id-1")
                                .name("Strona-18")
                                        .mxGraphModel(MxGraphModel.builder()
                                                .root(Root.builder()
                                                        .cells(List.of(
                                                                MxCell.builder()
                                                                        .id("Cell-1")
                                                                        .build(),
                                                                MxCell.builder()
                                                                        .id("Cell-2")
                                                                        .build()
                                                        )).objects(List.of(
                                                            ObjectWrapper.builder()
                                                                    .id("ob-1")
                                                                    .build()
                                                        ))
                                                        .build())
                                                .build())
                                .build(),
                        Diagram.builder()
                                .id("id-2")
                                .name("Strona-25")
                                .build()))
                .build();
        String xml = drawioGenerator.generateXml(mxfile);
        Files.writeString(Path.of(FILE_PATH_MX_FILE), xml);

    }

    private static MxGraphModel createBaseGraph() {
        return MxGraphModel.builder()
                .root(Root.builder()
                        .cells(List.of(MxCell.builder()
                                        .id("0")
                                        .build(),
                                MxCell.builder()
                                        .id("1")
                                        .parent("0")
                                        .build()))
                        .build())
                .build();
    }
}
