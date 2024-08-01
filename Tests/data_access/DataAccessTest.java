package data_access;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    private DataAccess dataAccess;
    private File file;

    @BeforeEach
    void init() throws IOException {
        file = new File("src/data_access/database.txt");
        dataAccess = new DataAccess(file);
        Files.writeString(Path.of(file.getPath()), "");
    }

    @Test
    void testSingleEntry() throws IOException {
        String mockGameStr = "AB,AB,AB:AB,AB:AB,AB:AB,AB:AB,AB:AB";
        dataAccess.saveGame(mockGameStr);
        assertEquals(mockGameStr, Files.readString(Path.of(file.getPath())));
    }

    @Test
    void testMultipleEntries() throws IOException {
        String mockGameStr1 = "AB,AB,AB:AB,AB:AB,AB:AB,AB:AB,AB:AB";
        String mockGameStr2 = "CD,CD,CD:CD,CD:CD,CD:CD,CD:CD,CD:CD";
        String mockGameStr3 = "randomTestStr";
        dataAccess.saveGame(mockGameStr1);
        dataAccess.saveGame(mockGameStr2);
        dataAccess.saveGame(mockGameStr3);
        assertEquals(String.join(",,,",mockGameStr3,mockGameStr2,mockGameStr1), Files.readString(Path.of(file.getPath())));
    }

    @AfterEach
    void tearDown() throws IOException {
        init();
    }

}
