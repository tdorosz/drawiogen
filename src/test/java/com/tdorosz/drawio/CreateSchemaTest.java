package com.tdorosz.drawio;

import com.tdorosz.drawio.component.basic.Arrow;
import com.tdorosz.drawio.component.BinaryState;
import com.tdorosz.drawio.component.basic.Rectangle;
import com.tdorosz.drawio.service.DrawioGenerator;
import com.tdorosz.drawio.model.MxCell;
import com.tdorosz.drawio.model.MxGeometry;
import com.tdorosz.drawio.model.MxGraphModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static com.tdorosz.drawio.component.DrawioColor.*;
import static com.tdorosz.drawio.component.DrawioColor.fromColor;

@SpringBootTest
@Slf4j
class CreateSchemaTest {

    private static final String FILE_PATH = "D:\\projekty\\java\\drawio\\src\\test\\resources\\examples\\generated-2.xml";


    @Autowired
    private DrawioGenerator drawioGenerator;

    @Test
    void createRectangle() throws IOException {
        MxGraphModel model = MxGraphModel.builder()
                .cells(List.of(
                        MxCell.builder()
                                .id("0")
                                .build(),
                        MxCell.builder()
                                .id("1")
                                .parent("0")
                                .build(),
                        MxCell.builder()
                                .id("2")
                                .parent("1")
                                .value("")
                                .style("rounded=1;whiteSpace=wrap;html=1;")
                                .vertex("1")
                                .mxGeometry(MxGeometry.builder()
                                        .x(100)
                                        .y(620)
                                        .width(120)
                                        .height(60)
                                        .as("geometry")
                                        .build())
                                .build()
                ))
                .build();

        String xml = drawioGenerator.generateXml(model);
        Files.writeString(Path.of(FILE_PATH), xml);
        log.info(xml);
    }

    @Test
    void createManyRectangles() throws IOException {
        MxGraphModel model = createBaseGraph();
        for (int i = 0; i < 10; i++) {
            model.addCell(MxCell.builder()
                    .id(UUID.randomUUID().toString())
                    .parent("1")
                    .style("rounded=1;whiteSpace=wrap;html=1;")
                    .vertex("1")
                    .value(Integer.toString(i))
                    .mxGeometry(MxGeometry.builder()
                            .x(100 * i)
                            .y(620)
                            .width(100)
                            .height(60)
                            .as("geometry")
                            .build())
                    .build());
        }

        String xml = drawioGenerator.generateXml(model);
        Files.writeString(Path.of(FILE_PATH), xml);

    }

    @Test
    void createConnectedRectanglesUsingComponents() throws IOException {
        MxGraphModel model = createBaseGraph();

        Rectangle r1 = Rectangle.newRectangle(100, 100).value("1")
                .style()
                .fillColor(fromColor(COLOR_CYAN))
                .styleEnd();
        Rectangle r2 = Rectangle.newRectangle(100, 400).value("2")
                .style()
                .glass(BinaryState.ON)
                .fillColor(fromColor(COLOR_MEDIUMSEAGREEN))
                .styleEnd();

        Rectangle r3 = Rectangle.newRectangle(400, 0).value("3")
                .style()
                .glass(BinaryState.OFF)
                .fillColor(fromColor(COLOR_MEDIUMSLATEBLUE))
                .styleEnd();

        Arrow arrow1 = Arrow.newArrow(r1.id(), r2.id());
        Arrow arrow2 = Arrow.newArrow(r1.id(), r3.id());
        Arrow arrow3 = Arrow.newArrow(r3.id(), r1.id());

        model.addCell(arrow1.toMxCell());
        model.addCell(arrow2.toMxCell());
        model.addCell(arrow3.toMxCell());
        model.addCell(r1.toMxCell());
        model.addCell(r2.toMxCell());
        model.addCell(r3.toMxCell());

        String xml = drawioGenerator.generateXml(model);
        Files.writeString(Path.of(FILE_PATH), xml);
    }


    private static MxGraphModel createBaseGraph() {
        return MxGraphModel.builder()
                .cells(List.of(MxCell.builder()
                                .id("0")
                                .build(),
                        MxCell.builder()
                                .id("1")
                                .parent("0")
                                .build())).build();
    }
}
