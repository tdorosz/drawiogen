package com.tdorosz.drawiogen;

import com.tdorosz.drawiogen.drawio.serialize.MxFileSerializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ReadClassesTest {

    private static final String PROJECT_PATH = "D:\\projekty\\java\\drawiogen\\src";
    private static final String FILE_PATH = "D:\\projekty\\java\\drawiogen\\src\\test\\resources\\examples\\generated-3.xml";

    private MxFileSerializer mxFileSerializer = new MxFileSerializer();
//
//    @Test
//    void createShapeForEachClass() throws IOException {
//        Path root = Paths.get(PROJECT_PATH);
//
//        Map<Path, String> contentMap = new HashMap<>();
//
//        Files.walkFileTree(root, new SimpleFileVisitor<>() {
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                if (file.toString().endsWith(".java")) {
//                    log.info("Found java file {}", file);
//                    String content = String.join("\n", Files.readAllLines(file));
//                    contentMap.put(file, content);
//                }
//                return FileVisitResult.CONTINUE;
//            }
//        });
//
//        log.info("Ready for processing!");
//        DrawioFile drawioFile = new DrawioFile();
//        DrawioPage page1 = drawioFile.addNewPage("Page1");
//
//
//        int i = 0;
//        String prevId = null;
//        for (Map.Entry<Path, String> entry : contentMap.entrySet()) {
//            Path fileName = entry.getKey().getFileName();
//
//            ClassDescription classDescription = new ClassDescription();
//
//            Pattern logPattern = Pattern.compile("^\\s*(log\\.(error|debug|info)\\(.*\\);)$");
//
//            List<String> list = entry.getValue().lines()
//                    .filter(line -> logPattern.matcher(line).matches())
//                    .map(String::trim)
//                    .toList();
//
//            classDescription
//                    .addLogLines(list)
//                    .classFullName(fileName.getFileName().toString())
//                    .position((i % 10) * 500, (i / 10) * 300);
//            page1.addUseCase(classDescription);
//
//
//            if (prevId != null) {
//                page1.addElement(Arrow.newArrow(prevId, classDescription.id()));
//            }
//            prevId = classDescription.id();
//
//            ++i;
//        }
//
//        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
//        Files.writeString(Path.of(FILE_PATH), xml);
//    }

//    @Test
//    void example_usecase() throws IOException {
//        DrawioFile drawioFile = new DrawioFile();
//        DrawioPage page1 = drawioFile.addNewPage("Page1");
//
//        List<String> logLines = new ArrayList<>();
//        logLines.add("log.debug(...)");
//        logLines.add("log.error(...)");
//        logLines.add("log.debug(...)");
//        logLines.add("log.error(...)");
//        logLines.add("log.debug(...)");
//        logLines.add("log.info(...)");
//
//
//        for (int i = 0; i < 5; i++) {
//            ClassDescription classDescription = new ClassDescription();
//            classDescription
//                    .addLogLines(logLines)
//                    .classFullName("java.main.ReadClassesTest" + i)
//                    .position(10, 100);
//            page1.addUseCase(classDescription);
//        }
//
//        String xml = mxFileSerializer.generateXml(drawioFile.toMxFile());
//        Files.writeString(Path.of(FILE_PATH), xml);
//    }
}
