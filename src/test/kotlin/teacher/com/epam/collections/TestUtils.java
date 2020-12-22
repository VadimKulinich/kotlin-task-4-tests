package teacher.com.epam.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public final class TestUtils {

    public static final String FILE_ADDRESS = "src\\main\\java\\com\\epam\\rd\\java\\basic\\practice1";
    public static final String PACKAGE = "com.epam.rd.java.basic.practice1";
    public static final int REQUIRED_FILE_AMOUNT = 8;

    private TestUtils() {

    }

    public static List<File> getAllFiles(File dir) {
        List<File> list = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                list.add(file);
            } else {
                list.addAll(getAllFiles(file));
            }
        }
        return list;
    }

    public static void checkFilePresence(List<File> files, List<String> requiredFileNames) {
        List<String> fileNames = files.stream().map(e -> e.getName().toString()).collect(Collectors.toList());
        if (!fileNames.containsAll(requiredFileNames)) {
            String missing = requiredFileNames.stream().filter(e -> !fileNames.contains(e)).collect(Collectors.toList())
                    .toString();
            fail("No files " + missing);
        }
    }

    public static void checkFileAmount(List<File> files) {
        if (files.size() != REQUIRED_FILE_AMOUNT) {
            fail("Wrong file amount");
        }
    }

    public static void checkForbiddenImports(File dir) throws IOException {
        List<File> files = getAllFiles(dir);

        for (File file : files) {
            Pattern p = Pattern.compile("(import)(.*)");
            Matcher m = p.matcher(fileContentAsString(file));
            while (m.find()) {
                fail("Forbidden classes presence");
                break;
            }
        }
    }

    private static String fileContentAsString(File file) throws IOException {
        return Files.lines(Paths.get(file.getAbsolutePath())).collect(Collectors.joining(System.lineSeparator()));
    }
}

